package com.narbase.oss.dto.common.entries

import sd.gov.moe.lp.dto.common.DtoName
import sd.gov.moe.lp.dto.common.EnumDtoName
import kotlin.js.JsExport


/*
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2022/04/02.
 */

enum class EntryTypes {
    TextInput,
    Mcq,
    CheckList,
    Image,
    Signature,
    Date,
    Group,
    Separator,
    TextView,
    Custom,
    DropDownList,
    RemoteDropDownList,
    CheckDropDownList,
    RemoteCheckDropDownList,
    File,
    PhoneInput,
    ;
    companion object {
        fun unsupported() = listOf(Image, Signature, Date, Group, Separator, TextView, Custom,
            RemoteCheckDropDownList, RemoteDropDownList, PhoneInput,
            CheckDropDownList, DropDownList)
    }
}
@JsExport
class EntryCondition(
    val rule: Operator,
    val action: DtoName<Action>,
    val message: String?,
) {
    /*
     * Rules: Array of ORs?
     *  - If entry_1 isBlank
     *  - If entry_1 is value
     *  - If entry_2 isNot value
     * Action:
     *  - Hide (All entries are shown by default)
     *  - IsRequired
     * Target (List of target entries affected by the condition)? maybe in the future:
     */

    class Operator(
        val type: DtoName<OperatorType>,

        // For operator
        val exp1: Operator?,
        val exp2: Operator?,

        //For expression:
        val value: String?,
    ) {

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Operator) return false

            return (this.type == other.type) &&
                    (this.exp1 == other.exp1) &&
                    (this.exp2 == other.exp2) &&
                    (this.value == other.value)
        }

        override fun hashCode(): Int {
            var result = type.hashCode()
            result = 31 * result + (exp1?.hashCode() ?: 0)
            result = 31 * result + (exp2?.hashCode() ?: 0)
            result = 31 * result + (value?.hashCode() ?: 0)
            return result
        }
    }

    enum class Action(dtoName: String? = null) : EnumDtoName {
        //        Hide,
        IsRequired,
        ;

        override val dtoName = dtoName ?: this.name
    }

    enum class OperatorType(dtoName: String? = null) : EnumDtoName {
        // OperatorType
        Equals,
        NotEquals,
        Or,
        And,

        // Simple types

        Entry,
        RegEx,
        Text,
        ;

        override val dtoName = dtoName ?: this.name

    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EntryCondition) return false

        return (this.rule == other.rule) &&
                (this.action == other.action) &&
                (this.message == other.message)
    }

    override fun hashCode(): Int {
        var result = rule.hashCode()
        result = 31 * result + action.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        return result
    }
}


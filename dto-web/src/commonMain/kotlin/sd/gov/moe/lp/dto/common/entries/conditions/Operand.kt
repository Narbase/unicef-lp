package com.narbase.oss.dto.common.entries.conditions

/*
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2024/03/09.
 */
sealed class Operand {
    fun equalsOperand(operand: Operand): Boolean {
        return when(operand) {
            is EntryOperand -> equalsEntry(operand)
            is RegexOperand -> equalsRegex(operand)
            is TextOperand -> equalsText(operand)
        }
    }

    protected abstract fun equalsRegex(operand: RegexOperand): Boolean
    protected abstract fun equalsText(operand: TextOperand): Boolean
    protected abstract fun equalsEntry(operand: EntryOperand): Boolean
}

class RegexOperand(
        val regex: Regex
) : Operand() {
    override fun equalsRegex(operand: RegexOperand): Boolean = regex == operand.regex

    override fun equalsText(operand: TextOperand): Boolean = regex.matches(operand.value)

    override fun equalsEntry(operand: EntryOperand): Boolean = regex.matches(operand.answerSummaryGetter())

}

class TextOperand(
        val value: String
) : Operand() {

    override fun equalsRegex(operand: RegexOperand): Boolean = operand.regex.matches(value)

    override fun equalsText(operand: TextOperand): Boolean = operand.value == value

    override fun equalsEntry(operand: EntryOperand): Boolean = operand.answerSummaryGetter().trim() == value.trim()
}


class EntryOperand(
        val answerSummaryGetter: () -> String
) : Operand() {

    override fun equalsRegex(operand: RegexOperand): Boolean = operand.regex.matches(answerSummaryGetter())

    override fun equalsText(operand: TextOperand): Boolean = answerSummaryGetter().trim() == operand.value.trim()

    override fun equalsEntry(operand: EntryOperand): Boolean = answerSummaryGetter().trim() == operand.answerSummaryGetter().trim()
}

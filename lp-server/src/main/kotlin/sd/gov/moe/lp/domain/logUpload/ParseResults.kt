package sd.gov.moe.lp.domain.logUpload

import org.joda.time.DateTime
import java.io.File

/*
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2023/10/09.
 */
class ParseResults {
    private val timestamp = DateTime().toString("ddMMyyyy_HHmmss")
    private val file = File("./files/log/log_$timestamp.txt").also { it.writeText("") }
    private val fileResults = mutableListOf<FileParseResults>()
    fun onNewFile(path: String): FileParseResults {
        log("Parsing $path")
        val newFile = FileParseResults(path, this)
        fileResults.add(newFile)
        return newFile
    }

    fun log(text: String) {
        file.appendText(text)
        file.appendText("\n")
    }

    fun getProcessingSummary(): String {
        return buildString {
            appendLine("Total files: ${fileResults.count()}")
            appendLine("Log file: ${file.name}")
            fileResults.forEach {
                appendLine(it.getProcessingSummary())
            }
        }
    }

    fun isEmpty(): Boolean {
        if (fileResults.isEmpty()) return true
        return fileResults.all { it.isEmpty() }
    }

    fun toDbData(states: List<String>) = ParseResultData(states, fileResults.map { it.toDbData() })

}

class FileParseResults(
    private val path: String,
    private val parseResults: ParseResults
) {
    private var failedFolders = 0
    private var insertedLogRows = 0
    private var failedLogRows = 0
    private var nonExistentStudentsCount = 0
    private var nonExistentStudentIds = mutableSetOf<String>()
    private var repeatEntries = 0

    fun isEmpty(): Boolean {
        return failedFolders == 0 &&
                insertedLogRows == 0 &&
                failedLogRows == 0 &&
                nonExistentStudentsCount == 0 &&
                repeatEntries == 0
    }

    fun log(text: String) {
        parseResults.log(text)
    }

    fun onFailedToParseFolder(path: String, message: String) {
        failedFolders++
        log("Failed to parse $path - $message")
    }

    fun onFailedToParseLogRow(path: String, message: String) {
        failedLogRows++
        log("Failed to parse row $path - $message")
    }

    fun onStudentIdDoesNotExist(path: String, message: String) {
        nonExistentStudentsCount++
        nonExistentStudentIds.add(message)
        log("Student ID does not exist $path - $message")
    }

    fun onRepeatEntry() {
        repeatEntries++
    }

    fun onInsertedEntry() {
        insertedLogRows++
    }

    fun getProcessingSummary(): String {
        return buildString {
            appendLine("File: $path")
            appendLine("Failed Folders: $failedFolders")
            appendLine("Inserted Log Rows: $insertedLogRows")
            appendLine("Failed Log Rows: $failedLogRows")
            appendLine("Non Existent Student IDs: ${nonExistentStudentIds.count()}: ${nonExistentStudentIds.joinToString()}")
            appendLine("Repeat Entries: $repeatEntries")

        }
    }

    fun toDbData() = FileParseResultsDbData(
        path,
        failedFolders,
        insertedLogRows,
        failedLogRows,
        nonExistentStudentsCount,
        nonExistentStudentIds,
        repeatEntries,
    )
}

class ParseResultData(
    val states: List<String>,
    val fileResults: List<FileParseResultsDbData>
)

class FileParseResultsDbData(
    val path: String,
    val failedFolders: Int = 0,
    val insertedLogRows: Int = 0,
    val failedLogRows: Int = 0,
    val nonExistentStudentsCount: Int = 0,
    val nonExistentStudentIds: Set<String>,
    val repeatEntries: Int = 0,
)

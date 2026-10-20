package dev.spaces.app.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class DateWindow(
    val startDate: LocalDate,
    val endDate: LocalDate
)

@Serializable
data class WeeklyReport(
    val authorName: String,
    val window: DateWindow,
    val majorHighlights: List<DevActivity>,
    val minorWork: List<DevActivity>,
    val otherWork: List<DevActivity>,
    val blockers: String = "",
    val personalNotes: String = ""
) {
    fun toMarkdown(): String {
        val sb = StringBuilder()
        sb.appendLine("## Weekly Progress Report: $authorName")
        sb.appendLine("**Period:** ${window.startDate} - ${window.endDate}")
        sb.appendLine()

        sb.appendLine("### Major Work")
        if (majorHighlights.isEmpty()) {
            sb.appendLine("- None")
        } else {
            majorHighlights.forEach {
                sb.appendLine("- **[${it.repository}]** ${it.title} ([ref](${it.url}))")
            }
        }
        sb.appendLine()

        sb.appendLine("### Minor & Maintenance")
        if (minorWork.isEmpty()) {
            sb.appendLine("- None")
        } else {
            minorWork.forEach {
                sb.appendLine("- **[${it.repository}]** ${it.title}")
            }
        }
        sb.appendLine()

        sb.appendLine("### Code Reviews & Support")
        if (otherWork.isEmpty()) {
            sb.appendLine("- None")
        } else {
            otherWork.forEach {
                sb.appendLine("- Reviewed: ${it.title} (${it.repository})")
            }
        }
        sb.appendLine()

        if (blockers.isNotBlank()) {
            sb.appendLine("### Blockers")
            sb.appendLine(blockers.trim())
            sb.appendLine()
        }

        if (personalNotes.isNotBlank()) {
            sb.appendLine("### Notes & Learning")
            sb.appendLine(personalNotes.trim())
            sb.appendLine()
        }

        return sb.toString().trimEnd()
    }
}

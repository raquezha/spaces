package dev.spaces.app.connector

import dev.spaces.app.model.ActivityType
import dev.spaces.app.model.DateWindow
import dev.spaces.app.model.DevActivity
import dev.spaces.app.model.WeeklyReport
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

class MockActivityConnector : ActivityConnector {
    override val id: String = "mock-connector"
    override val displayName: String = "Mock Workspace"
    override val hostUrl: String = "https://mock.spaces.dev"

    override suspend fun fetchActivity(window: DateWindow): Result<List<DevActivity>> {
        return Result.success(sampleActivities)
    }

    companion object {
        val sampleActivities = listOf(
            DevActivity(
                id = "act-1",
                connectorId = "mock-connector",
                repository = "sample-org/mobile-client",
                title = "feat: implement offline report draft caching",
                url = "https://github.com/sample-org/mobile-client/pull/101",
                type = ActivityType.MERGED_CHANGE,
                timestamp = Instant.fromEpochMilliseconds(1730000000000)
            ),
            DevActivity(
                id = "act-2",
                connectorId = "mock-connector",
                repository = "sample-org/mobile-client",
                title = "fix: resolve keychain entitlement warning in debug builds",
                url = "https://github.com/sample-org/mobile-client/pull/102",
                type = ActivityType.MERGED_CHANGE,
                timestamp = Instant.fromEpochMilliseconds(1730003600000)
            ),
            DevActivity(
                id = "act-3",
                connectorId = "mock-connector",
                repository = "sample-org/tooling-core",
                title = "chore(deps): bump kotlin to 2.1.10 and coroutines to 1.10.1",
                url = "https://github.com/sample-org/tooling-core/pull/54",
                type = ActivityType.MERGED_CHANGE,
                timestamp = Instant.fromEpochMilliseconds(1730010000000)
            ),
            DevActivity(
                id = "act-4",
                connectorId = "mock-connector",
                repository = "sample-org/mobile-client",
                title = "CR: refactor navigation flow for tablet split view",
                url = "https://github.com/sample-org/mobile-client/pull/99",
                type = ActivityType.CODE_REVIEW,
                timestamp = Instant.fromEpochMilliseconds(1730020000000)
            )
        )

        fun sampleReport(): WeeklyReport {
            return WeeklyReport(
                authorName = "Dev Jane",
                window = DateWindow(
                    startDate = LocalDate(2026, 10, 14),
                    endDate = LocalDate(2026, 10, 20)
                ),
                majorHighlights = listOf(sampleActivities[0]),
                minorWork = listOf(sampleActivities[1], sampleActivities[2]),
                otherWork = listOf(sampleActivities[3]),
                blockers = "None this week.",
                personalNotes = "Learned KMP StateFlow bridging to SwiftUI on iOS 18."
            )
        }
    }
}

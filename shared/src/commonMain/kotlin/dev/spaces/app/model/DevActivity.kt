package dev.spaces.app.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
enum class ActivityType {
    MERGED_CHANGE,
    OPEN_CHANGE,
    CODE_REVIEW,
    COMMIT,
    ISSUE
}

@Serializable
data class DevActivity(
    val id: String,
    val connectorId: String,
    val repository: String,
    val title: String,
    val url: String,
    val type: ActivityType,
    val timestamp: Instant,
    val metadata: Map<String, String> = emptyMap()
)

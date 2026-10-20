package dev.spaces.app.connector

import dev.spaces.app.model.DateWindow
import dev.spaces.app.model.DevActivity

interface ActivityConnector {
    val id: String
    val displayName: String
    val hostUrl: String

    suspend fun fetchActivity(window: DateWindow): Result<List<DevActivity>>
}

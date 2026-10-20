package dev.spaces.app.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import dev.spaces.app.android.ui.WeeklyReportScreen
import dev.spaces.app.connector.MockActivityConnector

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                WeeklyReportScreen(
                    report = MockActivityConnector.sampleReport()
                )
            }
        }
    }
}

package dev.spaces.app.android.ui

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.spaces.app.connector.MockActivityConnector
import dev.spaces.app.model.DevActivity
import dev.spaces.app.model.WeeklyReport

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeeklyReportScreen(
    report: WeeklyReport,
    modifier: Modifier = Modifier
) {
    val clipboardManager = LocalClipboardManager.current
    val context = LocalContext.current
    var blockers by remember { mutableStateOf(report.blockers) }
    var personalNotes by remember { mutableStateOf(report.personalNotes) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Spaces", fontWeight = FontWeight.Bold) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            Surface(
                tonalElevation = 8.dp,
                shadowElevation = 8.dp
            ) {
                Button(
                    onClick = {
                        val finalReport = report.copy(
                            blockers = blockers,
                            personalNotes = personalNotes
                        )
                        clipboardManager.setText(AnnotatedString(finalReport.toMarkdown()))
                        Toast.makeText(context, "Copied Markdown to clipboard!", Toast.LENGTH_SHORT).show()
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Icon(Icons.Default.ContentCopy, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Copy as Markdown", fontWeight = FontWeight.SemiBold)
                }
            }
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                start = 16.dp,
                end = 16.dp,
                top = innerPadding.calculateTopPadding() + 8.dp,
                bottom = innerPadding.calculateBottomPadding() + 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header card
            item {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(Icons.Default.DateRange, contentDescription = null)
                        Spacer(Modifier.width(12.dp))
                        Column {
                            Text(
                                text = "Weekly Report: ${report.authorName}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "${report.window.startDate} - ${report.window.endDate}",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }

            // Major section
            item {
                SectionHeader("Major Highlights (${report.majorHighlights.size})")
            }
            items(report.majorHighlights) { activity ->
                ActivityCard(activity)
            }

            // Minor section
            item {
                SectionHeader("Minor & Chores (${report.minorWork.size})")
            }
            items(report.minorWork) { activity ->
                ActivityCard(activity)
            }

            // Reviews section
            item {
                SectionHeader("Reviews & Support (${report.otherWork.size})")
            }
            items(report.otherWork) { activity ->
                ActivityCard(activity)
            }

            // Notes inputs
            item {
                SectionHeader("Blockers")
                OutlinedTextField(
                    value = blockers,
                    onValueChange = { blockers = it },
                    placeholder = { Text("Any blockers this week? (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    minLines = 2
                )
            }

            item {
                SectionHeader("Personal Notes & Learnings")
                OutlinedTextField(
                    value = personalNotes,
                    onValueChange = { personalNotes = it },
                    placeholder = { Text("What did you learn or explore? (optional)") },
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    minLines = 2
                )
            }
        }
    }
}

@Composable
fun SectionHeader(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleSmall,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(top = 8.dp)
    )
}

@Composable
fun ActivityCard(activity: DevActivity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
        )
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = activity.repository,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = activity.title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeeklyReportScreenPreview() {
    MaterialTheme {
        WeeklyReportScreen(
            report = MockActivityConnector.sampleReport()
        )
    }
}

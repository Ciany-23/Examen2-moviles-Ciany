package com.example.manejobugs.ui.screens.bugdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.manejobugs.ui.FeatureFlags
import com.example.manejobugs.ui.components.AppButton

@Composable
fun BugDetailScreen(viewModel: BugDetailViewModel, onBack: () -> Unit) {
    val bug by viewModel.bug.collectAsState()

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            when (val current = bug) {
                null -> {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(top = 80.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CircularProgressIndicator()
                    }
                }

                else -> {
                    Text(
                        text = current.title,
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )

                    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            DetailRow(label = "Description", value = current.description)
                            DetailRow(label = "System", value = current.affectedSystem)
                            DetailRow(label = "Category", value = current.category.name.replace("_", " "))
                            DetailRow(label = "Severity", value = current.severity.name)
                            DetailRow(label = "Priority", value = current.priority.name)
                            DetailRow(label = "Status", value = current.status.name)
                            DetailRow(label = "Created", value = current.createdAt.take(10))
                            DetailRow(label = "Updated", value = current.updatedAt.take(10))
                        }
                    }

                    if (FeatureFlags.ENABLE_STATUS_UPDATE) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            AppButton(
                                text = "Change status",
                                onClick = viewModel::toggleStatus,
                                modifier = Modifier.weight(1f)
                            )
                            AppButton(
                                text = "Priority",
                                onClick = viewModel::cyclePriority,
                                modifier = Modifier.weight(1f)
                            )
                        }
                    }

                    AppButton(text = "Back", onClick = onBack)
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String) {
    Column(verticalArrangement = Arrangement.spacedBy(2.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Medium
        )
    }
}

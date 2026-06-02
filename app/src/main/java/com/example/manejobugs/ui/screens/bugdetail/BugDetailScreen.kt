package com.example.manejobugs.ui.screens.bugdetail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
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
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            when (val current = bug) {
                null -> CircularProgressIndicator()
                else -> {
                    Text(current.title, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
                    Spacer(Modifier.height(12.dp))
                    Text(current.description)
                    Spacer(Modifier.height(12.dp))
                    Text("Severity: ${current.severity}")
                    Text("Priority: ${current.priority}")
                    Text("Status: ${current.status}")
                    Text("System: ${current.affectedSystem}")
                    Text("Category: ${current.category}")
                    Spacer(Modifier.height(24.dp))
                    if (FeatureFlags.ENABLE_STATUS_UPDATE) {
                        AppButton(text = "Toggle status", onClick = viewModel::toggleStatus)
                        Spacer(Modifier.height(8.dp))
                        AppButton(text = "Cycle priority", onClick = viewModel::cyclePriority)
                        Spacer(Modifier.height(8.dp))
                    }
                    AppButton(text = "Back", onClick = onBack)
                }
            }
        }
    }
}

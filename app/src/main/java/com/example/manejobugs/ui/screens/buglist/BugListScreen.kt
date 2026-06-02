package com.example.manejobugs.ui.screens.buglist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.manejobugs.domain.model.Bug
import com.example.manejobugs.ui.FeatureFlags
import com.example.manejobugs.ui.UiState
import com.example.manejobugs.ui.components.AppFloatingActionButton
import com.example.manejobugs.ui.components.PriorityChip
import com.example.manejobugs.ui.components.SeverityChip
import com.example.manejobugs.ui.components.StatusChip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BugListScreen(
    viewModel: BugListViewModel,
    onBugClick: (String) -> Unit,
    onCreateClick: () -> Unit
) {
    val bugsState by viewModel.bugsState.collectAsState()
    var showFlagsDialog by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bug Reports", fontWeight = FontWeight.Bold) },
                actions = {
                    TextButton(onClick = { showFlagsDialog = true }) {
                        Text("Flags")
                    }
                }
            )
        },
        floatingActionButton = {
            if (FeatureFlags.ENABLE_CREATE_BUG) {
                AppFloatingActionButton(onClick = onCreateClick)
            }
        }
    ) { padding ->
        when (val state = bugsState) {
            is UiState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            is UiState.Error -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = state.message,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            is UiState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item { Spacer(modifier = Modifier.height(4.dp)) }
                    items(state.data, key = { it.id }) { bug ->
                        BugCard(bug = bug, onClick = { onBugClick(bug.id) })
                    }
                    item { Spacer(modifier = Modifier.height(80.dp)) }
                }
            }
        }
    }

    if (showFlagsDialog) {
        AlertDialog(
            onDismissRequest = { showFlagsDialog = false },
            title = { Text("Feature Flags") },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Create bug")
                        Switch(
                            checked = FeatureFlags.ENABLE_CREATE_BUG,
                            onCheckedChange = { FeatureFlags.ENABLE_CREATE_BUG = it }
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Update status")
                        Switch(
                            checked = FeatureFlags.ENABLE_STATUS_UPDATE,
                            onCheckedChange = { FeatureFlags.ENABLE_STATUS_UPDATE = it }
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(onClick = { showFlagsDialog = false }) {
                    Text("Close")
                }
            }
        )
    }
}

@Composable
private fun BugCard(bug: Bug, onClick: () -> Unit) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = bug.affectedSystem,
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                SeverityChip(bug.severity)
            }

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = bug.title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                StatusChip(bug.status.name)
                PriorityChip(bug.priority)
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = bug.category.name.replace("_", " "),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = bug.createdAt.take(10),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

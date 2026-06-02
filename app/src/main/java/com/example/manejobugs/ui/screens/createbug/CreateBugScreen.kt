package com.example.manejobugs.ui.screens.createbug

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.manejobugs.domain.model.BugCategory
import com.example.manejobugs.domain.model.BugStatus
import com.example.manejobugs.domain.model.Priority
import com.example.manejobugs.domain.model.Severity
import com.example.manejobugs.ui.FeatureFlags
import com.example.manejobugs.ui.UiState
import com.example.manejobugs.ui.components.AppButton

@Composable
fun CreateBugScreen(viewModel: CreateBugViewModel, onBack: () -> Unit, onCreated: () -> Unit) {
    val state by viewModel.state.collectAsState()
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var affectedSystem by remember { mutableStateOf("") }
    var severity by remember { mutableStateOf(Severity.MEDIUM) }
    var priority by remember { mutableStateOf(Priority.MEDIUM) }
    var status by remember { mutableStateOf(BugStatus.OPEN) }
    var category by remember { mutableStateOf(BugCategory.UI) }
    var severityExpanded by remember { mutableStateOf(false) }
    var priorityExpanded by remember { mutableStateOf(false) }
    var statusExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }

    LaunchedEffect(state) {
        if (state is UiState.Success) onCreated()
    }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(20.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Create Bug Report",
                style = androidx.compose.material3.MaterialTheme.typography.headlineSmall
            )

            ElevatedCard(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedTextField(
                        value = title,
                        onValueChange = { title = it },
                        label = { Text("Title") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = description,
                        onValueChange = { description = it },
                        label = { Text("Description") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = affectedSystem,
                        onValueChange = { affectedSystem = it },
                        label = { Text("Affected system") },
                        modifier = Modifier.fillMaxWidth()
                    )

                    FieldDropdown(
                        label = "Severity",
                        value = severity.name,
                        expanded = severityExpanded,
                        onExpandedChange = { severityExpanded = it },
                        options = Severity.entries.map { it.name },
                        onOptionSelected = { severity = Severity.valueOf(it) }
                    )

                    FieldDropdown(
                        label = "Priority",
                        value = priority.name,
                        expanded = priorityExpanded,
                        onExpandedChange = { priorityExpanded = it },
                        options = Priority.entries.map { it.name },
                        onOptionSelected = { priority = Priority.valueOf(it) }
                    )

                    FieldDropdown(
                        label = "Status",
                        value = status.name,
                        expanded = statusExpanded,
                        onExpandedChange = { statusExpanded = it },
                        options = BugStatus.entries.map { it.name },
                        onOptionSelected = { status = BugStatus.valueOf(it) }
                    )

                    FieldDropdown(
                        label = "Category",
                        value = category.name,
                        expanded = categoryExpanded,
                        onExpandedChange = { categoryExpanded = it },
                        options = BugCategory.entries.map { it.name },
                        onOptionSelected = { category = BugCategory.valueOf(it) }
                    )
                }
            }

            AppButton(
                text = "Create bug",
                loading = state is UiState.Loading,
                onClick = {
                    if (FeatureFlags.ENABLE_CREATE_BUG) {
                        viewModel.createBug(
                            title = title,
                            description = description,
                            severity = severity,
                            priority = priority,
                            status = status,
                            affectedSystem = affectedSystem,
                            category = category
                        )
                    }
                }
            )

            AppButton(text = "Back", onClick = onBack)
            Spacer(Modifier.height(8.dp))
        }
    }
}

@Composable
private fun FieldDropdown(
    label: String,
    value: String,
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    options: List<String>,
    onOptionSelected: (String) -> Unit
) {
    Box(modifier = Modifier.fillMaxWidth().clickable { onExpandedChange(!expanded) }) {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text(label) },
            modifier = Modifier.fillMaxWidth()
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { onExpandedChange(false) }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option) },
                    onClick = {
                        onOptionSelected(option)
                        onExpandedChange(false)
                    }
                )
            }
        }
    }
}

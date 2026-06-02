package com.example.manejobugs.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.manejobugs.domain.model.Priority
import com.example.manejobugs.domain.model.Severity

@Composable
fun SeverityChip(severity: Severity) {
    val color = when (severity) {
        Severity.CRITICAL -> Color(0xFFD32F2F)
        Severity.HIGH -> Color(0xFFF57C00)
        Severity.MEDIUM -> Color(0xFFF9A825)
        Severity.LOW -> Color(0xFF388E3C)
    }
    SuggestionChip(
        onClick = {},
        label = { Text(severity.name, style = MaterialTheme.typography.labelSmall) },
        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = color.copy(alpha = 0.15f)),
        border = BorderStroke(1.dp, color)
    )
}

@Composable
fun StatusChip(status: String) {
    SuggestionChip(
        onClick = {},
        label = { Text(status.replace("_", " "), style = MaterialTheme.typography.labelSmall) }
    )
}

@Composable
fun PriorityChip(priority: Priority) {
    val color = when (priority) {
        Priority.URGENT -> Color(0xFFD32F2F)
        Priority.HIGH -> Color(0xFFF57C00)
        Priority.MEDIUM -> Color(0xFFF9A825)
        Priority.LOW -> Color(0xFF388E3C)
    }
    SuggestionChip(
        onClick = {},
        label = { Text("P: ${priority.name}", style = MaterialTheme.typography.labelSmall) },
        colors = SuggestionChipDefaults.suggestionChipColors(containerColor = color.copy(alpha = 0.10f)),
        border = BorderStroke(1.dp, color.copy(alpha = 0.5f))
    )
}

package com.example.manejobugs.ui.screens.createbug

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.manejobugs.ui.UiState
import com.example.manejobugs.ui.components.AppButton

@Composable
fun CreateBugScreen(viewModel: CreateBugViewModel, onBack: () -> Unit, onCreated: () -> Unit) {
    val state by viewModel.state.collectAsState()
    LaunchedEffect(state) {
        if (state is UiState.Success) onCreated()
    }
    Scaffold { padding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(padding).padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Create Bug Report")
            Spacer(Modifier.height(16.dp))
            AppButton(
                text = "Create sample bug",
                loading = state is UiState.Loading,
                onClick = viewModel::createBug
            )
            Spacer(Modifier.height(12.dp))
            AppButton(text = "Back", onClick = onBack)
        }
    }
}

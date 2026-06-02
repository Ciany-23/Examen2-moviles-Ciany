package com.example.manejobugs.ui.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.manejobugs.ui.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _loginState = MutableStateFlow<UiState<Unit>?>(null)
    val loginState = _loginState.asStateFlow()

    /**
     * Simulates an authentication call.
     * Accepts any non-empty email/password for the PoC.
     * Replace the body of this function with a real API call when the backend is ready.
     */
    fun login(email: String, password: String) {
        if (email.isBlank() || password.isBlank()) {
            _loginState.value = UiState.Error("Email and password are required.")
            return
        }
        viewModelScope.launch {
            _loginState.value = UiState.Loading
            delay(800) // Simulates network request
            _loginState.value = UiState.Success(Unit)
        }
    }

    fun resetState() {
        _loginState.value = null
    }
}
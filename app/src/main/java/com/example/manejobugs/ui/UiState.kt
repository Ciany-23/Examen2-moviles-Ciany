package com.example.manejobugs.ui

/**
 * Generic UI state wrapper used by all ViewModels.
 * Covers the three states any async operation can be in: loading, success, or error.
 */
sealed class UiState<out T> {
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Error(val message: String) : UiState<Nothing>()
}
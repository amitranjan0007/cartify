package org.cartify.ecommerce.feature_home.presentation.viewmodel

import androidx.compose.runtime.collectAsState
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject


@HiltViewModel
class HomeScreenViewModel @Inject constructor():ViewModel() {
    private val state = MutableStateFlow(false)
    val _state = state.asStateFlow()
}
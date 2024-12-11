package br.com.rocketseat.hiokdev.nearby.ui.screen.home

sealed class HomeUiEvent {
    data object OnFetchCategories : HomeUiEvent()
    data class OnFetchMarkets(val categoryId: String) : HomeUiEvent()
}

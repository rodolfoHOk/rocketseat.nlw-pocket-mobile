package br.com.rocketseat.hiokdev.nearby.ui.screen.market_details

import br.com.rocketseat.hiokdev.nearby.data.model.Rule

data class MarketDetailsUiState(
    val rules: List<Rule>? = null,
    val coupon: String? = null,
)

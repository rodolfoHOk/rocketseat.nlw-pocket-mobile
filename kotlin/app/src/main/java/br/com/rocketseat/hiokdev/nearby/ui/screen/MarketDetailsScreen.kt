package br.com.rocketseat.hiokdev.nearby.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.rocketseat.hiokdev.nearby.R
import br.com.rocketseat.hiokdev.nearby.data.model.Category
import br.com.rocketseat.hiokdev.nearby.data.model.Market
import br.com.rocketseat.hiokdev.nearby.data.model.mock.mockCategories
import br.com.rocketseat.hiokdev.nearby.data.model.mock.mockMarkets
import br.com.rocketseat.hiokdev.nearby.ui.component.button.NearbyButton
import br.com.rocketseat.hiokdev.nearby.ui.component.market_details.NearbyMarketDetailsCoupons
import br.com.rocketseat.hiokdev.nearby.ui.component.market_details.NearbyMarketDetailsCouponsNumber
import br.com.rocketseat.hiokdev.nearby.ui.component.market_details.NearbyMarketDetailsInfos
import br.com.rocketseat.hiokdev.nearby.ui.component.market_details.NearbyMarketDetailsRules
import br.com.rocketseat.hiokdev.nearby.ui.theme.GreenBase
import br.com.rocketseat.hiokdev.nearby.ui.theme.Typography
import coil3.compose.AsyncImage

@Composable
fun MarketDetailsScreen(modifier: Modifier = Modifier, market: Market, onNavigateBack: () -> Unit) {
    Box(
        modifier = modifier.fillMaxSize()
    ) {
        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f),
            contentDescription = "Imagem do Local",
            contentScale = ContentScale.Crop,
            model = market.cover
        )

        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(topEnd = 16.dp, topStart = 16.dp))
                .fillMaxWidth()
                .fillMaxHeight(0.75f)
                .align(Alignment.BottomCenter)
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(36.dp)
            ) {
                Column {
                    Row (
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = market.name, style = Typography.headlineLarge)

//                        category.icon?.let {
//                            Icon(
//                                modifier = Modifier.size(20.dp),
//                                painter = painterResource(id = it),
//                                tint = GreenBase,
//                                contentDescription = "Ícone de Categoria"
//                            )
//                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(text = market.description, style = Typography.bodyLarge)

                    Spacer(modifier = Modifier.height(16.dp))

                    NearbyMarketDetailsCouponsNumber(numberOfCoupons = market.coupons)
                }

                Spacer(modifier = Modifier.height(48.dp))

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
//                    if (market.rules.isNotEmpty()) {
//                        NearbyMarketDetailsRules(rules = market.rules)
//
//                        HorizontalDivider(
//                            modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
//                        )
//                    }

                    NearbyMarketDetailsInfos(market = market)

                    HorizontalDivider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp)
                    )

                    NearbyMarketDetailsCoupons(coupons = listOf("ABC12345"))
                }

                NearbyButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    text = "Ler QR Code",
                    onClick = { }
                )
            }
        }

        NearbyButton(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(24.dp),
            iconRes = R.drawable.ic_arrow_left,
            onClick = onNavigateBack
        )
    }
}

@Preview
@Composable
private fun MarketDetailsScreenPreview() {
    MarketDetailsScreen(market = mockMarkets.first(), onNavigateBack = {})
}

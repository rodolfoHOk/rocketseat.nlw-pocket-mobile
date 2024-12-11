package br.com.rocketseat.hiokdev.nearby.ui.component.market_details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.rocketseat.hiokdev.nearby.R
import br.com.rocketseat.hiokdev.nearby.ui.theme.RedBase
import br.com.rocketseat.hiokdev.nearby.ui.theme.RedLight
import br.com.rocketseat.hiokdev.nearby.ui.theme.Typography

@Composable
fun NearbyMarketDetailsCouponsNumber(modifier: Modifier = Modifier, numberOfCoupons: Int) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(48.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(RedLight)
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                painter = painterResource(R.drawable.ic_ticket),
                tint = RedBase,
                contentDescription = "Ícone Cupons"
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(text = numberOfCoupons.toString(), style = Typography.titleSmall)

                Text(text = "cupons disponíveis" , style = Typography.bodySmall)
            }
        }

}

@Preview
@Composable
private fun NearbyMarketDetailsCouponsNumberPreview() {
    NearbyMarketDetailsCouponsNumber(
        modifier = Modifier.fillMaxWidth(),
        numberOfCoupons = 3
    )
}

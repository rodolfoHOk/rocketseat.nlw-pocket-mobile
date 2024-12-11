package br.com.rocketseat.hiokdev.nearby.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.rocketseat.hiokdev.nearby.ui.component.button.NearbyButton
import br.com.rocketseat.hiokdev.nearby.ui.component.welcome.WelcomeContent
import br.com.rocketseat.hiokdev.nearby.ui.component.welcome.WelcomeHeader

@Composable
fun WelcomeScreen(modifier: Modifier = Modifier, onNavigateToHome: () -> Unit) {
    Column(
        modifier = modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(horizontal = 40.dp, vertical = 48.dp)
            .verticalScroll(state = rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            WelcomeHeader()

            Spacer(modifier = Modifier.height(40.dp))

            WelcomeContent()
        }

        NearbyButton(
            modifier = Modifier.fillMaxWidth(),
            text = "Começar",
            onClick = onNavigateToHome
        )
    }
}

@Preview
@Composable
private fun WelcomeScreenPreview() {
    WelcomeScreen(onNavigateToHome = {})
}

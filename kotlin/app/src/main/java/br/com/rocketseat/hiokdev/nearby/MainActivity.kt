package br.com.rocketseat.hiokdev.nearby

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import br.com.rocketseat.hiokdev.nearby.data.model.Market
import br.com.rocketseat.hiokdev.nearby.ui.screen.home.HomeScreen
import br.com.rocketseat.hiokdev.nearby.ui.screen.market_details.MarketDetailsScreen
import br.com.rocketseat.hiokdev.nearby.ui.screen.SplashScreen
import br.com.rocketseat.hiokdev.nearby.ui.screen.WelcomeScreen
import br.com.rocketseat.hiokdev.nearby.ui.route.Home
import br.com.rocketseat.hiokdev.nearby.ui.route.Splash
import br.com.rocketseat.hiokdev.nearby.ui.route.Welcome
import br.com.rocketseat.hiokdev.nearby.ui.theme.NearbyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            NearbyTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Splash
                ) {
                    composable<Splash> {
                        SplashScreen(
                            onNavigateToWelcome = {
                                navController.navigate(Welcome)
                            }
                        )
                    }

                    composable<Welcome>() {
                        WelcomeScreen(
                            onNavigateToHome = {
                                navController.navigate(Home)
                            }
                        )
                    }

                    composable<Home> {
                        HomeScreen(
                            onNavigateToMarketDetails = { selectedMarket ->
                                navController.navigate(selectedMarket)
                            }
                        )
                    }

                    composable<Market> {
                        val selectedMarket = it.toRoute<Market>()

                        MarketDetailsScreen(
                            market = selectedMarket,
                            onNavigateBack = {
                                navController.popBackStack()
                            }
                        )
                    }
                }
            }
        }
    }
}

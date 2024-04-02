package projects.nyinyihtunlwin.diyrecipes.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import projects.nyinyihtunlwin.cocktails.presentation.screens.details.CocktailDetailsEvent
import projects.nyinyihtunlwin.cocktails.presentation.screens.details.CocktailDetailsScreen
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Destination
@Composable
fun CocktailDetails(
    drinkId: String,
    navigator: DestinationsNavigator,
) {
    DiyRecipesTheme {
        CocktailDetailsScreen(
            drinkId = drinkId,
            onEvent = {
                when (it) {
                    is CocktailDetailsEvent.Exit -> {
                        navigator.navigateUp()
                    }

                    else -> {}
                }
            },
        )
    }
}
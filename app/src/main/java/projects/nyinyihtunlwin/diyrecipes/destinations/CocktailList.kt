package projects.nyinyihtunlwin.diyrecipes.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import projects.nyinyihtunlwin.cocktails.presentation.screens.listing.CocktailListEvent
import projects.nyinyihtunlwin.cocktails.presentation.screens.listing.CocktailListScreen
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Destination
@Composable
fun CocktailList(
    navigator: DestinationsNavigator,
) {
    DiyRecipesTheme {
        CocktailListScreen(
            onEvent = {
                when (it) {
                    is CocktailListEvent.Exit -> {
                        navigator.navigateUp()
                    }

                    else -> {}
                }
            },
        )
    }
}
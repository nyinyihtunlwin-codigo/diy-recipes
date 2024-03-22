package projects.nyinyihtunlwin.diyrecipes.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.meals.presentation.screens.listing.MealListEvent
import projects.nyinyihtunlwin.meals.presentation.screens.listing.MealListScreen
@Destination
@Composable
fun MealList(
    navigator: DestinationsNavigator,
) {
    DiyRecipesTheme {
        MealListScreen(
            onEvent = {
                when (it) {
                    is MealListEvent.Exit -> {
                        navigator.navigateUp()
                    }
                    else -> {}
                }
            },
        )
    }
}
package projects.nyinyihtunlwin.diyrecipes.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.meals.presentation.screens.details.MealDetailsEvent
import projects.nyinyihtunlwin.meals.presentation.screens.details.MealDetailsScreen

@Destination
@Composable
fun MealDetails(
    mealId: String,
    navigator: DestinationsNavigator,
) {
    DiyRecipesTheme {
        MealDetailsScreen(
            mealId = mealId,
            onEvent = {
                when (it) {
                    is MealDetailsEvent.Exit -> {
                        navigator.navigateUp()
                    }

                    else -> {}
                }
            },
        )
    }
}
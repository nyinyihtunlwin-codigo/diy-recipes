package projects.nyinyihtunlwin.diyrecipes.destinations

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme
import projects.nyinyihtunlwin.meals.presentation.screens.category.MealCategoryListEvent
import projects.nyinyihtunlwin.meals.presentation.screens.category.MealCategoryListScreen

@RootNavGraph(start = true)
@Destination
@Composable
fun MealCategoryList(
    navigator: DestinationsNavigator,
) {
    DiyRecipesTheme {
        MealCategoryListScreen(
            onEvent = {
                when (it) {
                    is MealCategoryListEvent.Exit -> {
                        navigator.navigateUp()
                    }
                    else -> {}
                }
            },
        )
    }
}
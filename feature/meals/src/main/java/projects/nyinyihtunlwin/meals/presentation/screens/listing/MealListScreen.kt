package projects.nyinyihtunlwin.meals.presentation.screens.listing

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Composable
fun MealListScreen(
    onEvent: (MealListEvent) -> Unit = {}
) {
    MealListContent()
}

@Composable
fun MealListContent() {

}

@Preview(showBackground = true)
@Composable
fun MealListContentPreview() {
    DiyRecipesTheme {
        MealListContent()
    }
}
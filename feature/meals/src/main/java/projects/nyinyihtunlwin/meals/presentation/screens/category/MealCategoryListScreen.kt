package projects.nyinyihtunlwin.meals.presentation.screens.category

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Composable
fun MealCategoryListScreen(
    onEvent: (MealCategoryListEvent) -> Unit = {}
) {
    MealCategoryListContent()
}

@Composable
fun MealCategoryListContent() {

}

@Preview(showBackground = true)
@Composable
fun MealCategoryListContentPreview() {
    DiyRecipesTheme {
        MealCategoryListContent()
    }
}
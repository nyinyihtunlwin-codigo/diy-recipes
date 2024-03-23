package projects.nyinyihtunlwin.meals.presentation.screens.category

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesToolbar
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Composable
fun MealCategoryListScreen(
    onEvent: (MealCategoryListEvent) -> Unit = {}
) {
    MealCategoryListContent()
}

@Composable
fun MealCategoryListContent() {
    Scaffold(
        topBar = {
            DiyRecipesToolbar(title = "DIY Recipes", subTitle = "Meals")
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun MealCategoryListContentPreview() {
    DiyRecipesTheme {
        MealCategoryListContent()
    }
}
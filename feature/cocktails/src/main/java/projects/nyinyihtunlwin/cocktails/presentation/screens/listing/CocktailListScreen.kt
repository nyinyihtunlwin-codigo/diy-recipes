package projects.nyinyihtunlwin.cocktails.presentation.screens.listing

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import projects.nyinyihtunlwin.designsystem.components.DiyRecipesToolbar
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Composable
fun CocktailListScreen(
    onEvent: (CocktailListEvent) -> Unit = {}
) {
    CocktailListContent()
}

@Composable
fun CocktailListContent() {
    Scaffold(
        topBar = {
            DiyRecipesToolbar(title = "DIY Recipes", subTitle = "Cocktails")
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
fun CocktailListContentPreview() {
    DiyRecipesTheme {
        CocktailListContent()
    }
}
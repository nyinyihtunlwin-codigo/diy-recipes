package projects.nyinyihtunlwin.cocktails.presentation.screens.listing

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import projects.nyinyihtunlwin.designsystem.theme.DiyRecipesTheme

@Composable
fun CocktailListScreen(
    onEvent: (CocktailListEvent) -> Unit = {}
) {
    CocktailListContent()
}

@Composable
fun CocktailListContent() {

}

@Preview(showBackground = true)
@Composable
fun CocktailListContentPreview() {
    DiyRecipesTheme {
        CocktailListContent()
    }
}
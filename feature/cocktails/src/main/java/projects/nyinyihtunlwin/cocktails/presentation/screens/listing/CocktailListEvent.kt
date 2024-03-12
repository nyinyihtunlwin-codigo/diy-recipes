package projects.nyinyihtunlwin.cocktails.presentation.screens.listing

sealed interface CocktailListEvent {
    data object Exit : CocktailListEvent

}
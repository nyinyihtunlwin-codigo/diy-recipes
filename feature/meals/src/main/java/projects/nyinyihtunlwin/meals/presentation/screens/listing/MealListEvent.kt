package projects.nyinyihtunlwin.meals.presentation.screens.listing

sealed interface MealListEvent {
    data object Exit : MealListEvent

}
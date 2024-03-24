package projects.nyinyihtunlwin.meals.presentation.screens.category

sealed interface MealCategoryListEvent {
    data class MealList(val categoryId: String, val categoryName: String) : MealCategoryListEvent
}
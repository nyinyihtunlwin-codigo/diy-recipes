package projects.nyinyihtunlwin.meals.presentation.mapper

import projects.nyinyihtunlwin.meals.domain.model.Meal
import projects.nyinyihtunlwin.meals.domain.model.MealCategory
import projects.nyinyihtunlwin.meals.presentation.model.MealCategoryUiModel
import projects.nyinyihtunlwin.meals.presentation.model.MealUiModel

fun MealCategory.toUiModel(): MealCategoryUiModel {
    return MealCategoryUiModel(
        idCategory = idCategory,
        strCategory = strCategory,
        strCategoryThumb = strCategoryThumb,
        strCategoryDescription = strCategoryDescription
    )
}

fun Meal.toUiModel(): MealUiModel {
    return MealUiModel(
        idMeal = idMeal,
        strMeal = strMeal,
        strDrinkAlternate = strDrinkAlternate,
        strCategory = strCategory,
        strArea = strArea,
        strInstructions = strInstructions,
        strMealThumb = strMealThumb,
        strTags = strTags,
        strYoutube = strYoutube,
        strIngredients = strIngredients,
        strMeasures = strMeasures,
        strSource = strSource,
        strImageSource = strImageSource,
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
        dateModified = dateModified
    )
}
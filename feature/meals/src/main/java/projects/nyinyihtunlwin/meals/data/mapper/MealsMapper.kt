package projects.nyinyihtunlwin.meals.data.mapper

import projects.nyinyihtunlwin.meals.data.model.MealCategoryListResponse
import projects.nyinyihtunlwin.meals.data.model.MealCategoryResponse
import projects.nyinyihtunlwin.meals.data.model.MealListResponse
import projects.nyinyihtunlwin.meals.data.model.MealResponse
import projects.nyinyihtunlwin.meals.domain.model.Meal
import projects.nyinyihtunlwin.meals.domain.model.MealCategory
import projects.nyinyihtunlwin.meals.domain.model.MealCategoryListData
import projects.nyinyihtunlwin.meals.domain.model.MealListData

fun MealCategoryListResponse?.toModel(): MealCategoryListData {
    return MealCategoryListData(
        categories = this?.categories.orEmpty().map {
            it.toModel()
        })
}

fun MealCategoryResponse.toModel(): MealCategory {
    return MealCategory(
        idCategory = idCategory ?: "",
        strCategory = strCategory ?: "",
        strCategoryThumb = strCategoryThumb ?: "",
        strCategoryDescription = strCategoryDescription ?: ""
    )
}

fun MealListResponse?.toModel(): MealListData {
    return MealListData(
        meals = this?.meals.orEmpty().map {
            it.toModel()
        })
}

fun MealResponse.toModel(): Meal {
    return Meal(
        idMeal = idMeal ?: "",
        strMeal = strMeal ?: "",
        strDrinkAlternate = strDrinkAlternate ?: "",
        strCategory = strCategory ?: "",
        strArea = strArea ?: "",
        strInstructions = strInstructions ?: "",
        strMealThumb = strMealThumb ?: "",
        strTags = strTags ?: "",
        strYoutube = strYoutube ?: "",
        strIngredients = listOf(
            strIngredient1 ?: "",
            strIngredient2 ?: "",
            strIngredient3 ?: "",
            strIngredient4 ?: "",
            strIngredient5 ?: "",
            strIngredient6 ?: "",
            strIngredient7 ?: "",
            strIngredient8 ?: "",
            strIngredient9 ?: "",
            strIngredient10 ?: "",
            strIngredient11 ?: "",
            strIngredient12 ?: "",
            strIngredient13 ?: "",
            strIngredient14 ?: "",
            strIngredient15 ?: "",
            strIngredient16 ?: "",
            strIngredient17 ?: "",
            strIngredient18 ?: "",
            strIngredient19 ?: "",
            strIngredient20 ?: ""
        ),
        strMeasures = listOf(
            strMeasure1 ?: "",
            strMeasure2 ?: "",
            strMeasure3 ?: "",
            strMeasure4 ?: "",
            strMeasure5 ?: "",
            strMeasure6 ?: "",
            strMeasure7 ?: "",
            strMeasure8 ?: "",
            strMeasure9 ?: "",
            strMeasure10 ?: "",
            strMeasure11 ?: "",
            strMeasure12 ?: "",
            strMeasure13 ?: "",
            strMeasure14 ?: "",
            strMeasure15 ?: "",
            strMeasure16 ?: "",
            strMeasure17 ?: "",
            strMeasure18 ?: "",
            strMeasure19 ?: "",
            strMeasure20 ?: ""
        ),
        strSource = strSource ?: "",
        strImageSource = strImageSource ?: "",
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed ?: "",
        dateModified = dateModified ?: ""
    )
}
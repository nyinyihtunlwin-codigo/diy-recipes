package projects.nyinyihtunlwin.cocktails.data.mapper

import projects.nyinyihtunlwin.cocktails.data.model.response.DrinkListResponse
import projects.nyinyihtunlwin.cocktails.data.model.response.DrinkResponse
import projects.nyinyihtunlwin.cocktails.domain.model.Drink
import projects.nyinyihtunlwin.cocktails.domain.model.DrinkListData

fun DrinkListResponse?.toModel(): DrinkListData {
    return DrinkListData(
        drinks = this?.drinks.orEmpty().map {
            it.toModel()
        })
}

fun DrinkResponse.toModel(): Drink {
    return Drink(
        idDrink = idDrink ?: "",
        strDrink = strDrink ?: "",
        strDrinkAlternate = strDrinkAlternate ?: "",
        strCategory = strCategory ?: "",
        strAlcoholic = strAlcoholic ?: "",
        strInstructions = strInstructions ?: "",
        strGlass = strGlass ?: "",
        strTags = strTags ?: "",
        strIBA = strIBA ?: "",
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
            strIngredient15 ?: ""
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
            strMeasure15 ?: ""
        ),
        strVideo = strVideo ?: "",
        strDrinkThumb = strDrinkThumb ?: "",
        strImageAttribution = strImageAttribution ?: "",
        strImageSource = strImageSource ?: "",
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed ?: "",
        dateModified = dateModified ?: ""
    )
}
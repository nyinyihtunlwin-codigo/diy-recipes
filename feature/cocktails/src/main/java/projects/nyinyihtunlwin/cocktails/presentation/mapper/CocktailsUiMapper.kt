package projects.nyinyihtunlwin.cocktails.presentation.mapper

import projects.nyinyihtunlwin.cocktails.domain.model.Drink
import projects.nyinyihtunlwin.cocktails.presentation.model.DrinkUiModel

fun Drink.toUiModel(): DrinkUiModel {
    return DrinkUiModel(
        idDrink = idDrink,
        strDrink = strDrink,
        strDrinkAlternate = strDrinkAlternate,
        strCategory = strCategory,
        strAlcoholic = strAlcoholic,
        strInstructions = strInstructions,
        strGlass = strGlass,
        strTags = strTags,
        strIBA = strIBA,
        strIngredients = strIngredients,
        strMeasures = strMeasures,
        strVideo = strVideo,
        strDrinkThumb = strDrinkThumb,
        strImageAttribution = strImageAttribution,
        strImageSource = strImageSource,
        strCreativeCommonsConfirmed = strCreativeCommonsConfirmed,
        dateModified = dateModified
    )
}
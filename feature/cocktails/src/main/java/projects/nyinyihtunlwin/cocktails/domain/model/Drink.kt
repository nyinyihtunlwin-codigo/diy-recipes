package projects.nyinyihtunlwin.cocktails.domain.model

class Drink(
    val idDrink: String,
    val strDrink: String,
    val strDrinkAlternate: String,
    val strTags: String,
    val strVideo: String,
    val strCategory: String,
    val strIBA: String,
    val strAlcoholic: String,
    val strGlass: String,
    val strInstructions: String,
    val strDrinkThumb: String,
    val strIngredients: List<String>,
    val strMeasures: List<String>,
    val strImageSource: String,
    val strImageAttribution: String,
    val strCreativeCommonsConfirmed: String,
    val dateModified: String
)


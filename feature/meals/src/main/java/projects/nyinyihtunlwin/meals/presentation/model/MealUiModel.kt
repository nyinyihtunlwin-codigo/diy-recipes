package projects.nyinyihtunlwin.meals.presentation.model

data class MealUiModel(
    val idMeal: String,
    val strMeal: String,
    val strDrinkAlternate: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String,
    val strYoutube: String,
    val strIngredients: List<String>,
    val strMeasures: List<String>,
    val strSource: String,
    val strImageSource: String,
    val strCreativeCommonsConfirmed: String,
    val dateModified: String,
)
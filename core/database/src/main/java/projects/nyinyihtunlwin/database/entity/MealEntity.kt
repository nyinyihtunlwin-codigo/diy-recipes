package projects.nyinyihtunlwin.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meals")
data class MealEntity(
    @PrimaryKey(autoGenerate = false)
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
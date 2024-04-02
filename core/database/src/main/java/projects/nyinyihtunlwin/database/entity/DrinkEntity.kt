package projects.nyinyihtunlwin.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinks")
data class DrinkEntity(
    @PrimaryKey(autoGenerate = false)
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
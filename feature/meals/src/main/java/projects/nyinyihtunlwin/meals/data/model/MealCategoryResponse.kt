package projects.nyinyihtunlwin.meals.data.model

import kotlinx.serialization.Serializable

@Serializable
class MealCategoryResponse(
    val idCategory: String?,
    val strCategory: String?,
    val strCategoryThumb: String?,
    val strCategoryDescription: String?,
)


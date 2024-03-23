package projects.nyinyihtunlwin.meals.data.model

import kotlinx.serialization.Serializable

@Serializable
class MealCategoryListResponse(
    val categories: List<MealCategoryResponse>?,
)


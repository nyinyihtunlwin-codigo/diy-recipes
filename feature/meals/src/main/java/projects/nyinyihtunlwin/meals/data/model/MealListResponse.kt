package projects.nyinyihtunlwin.meals.data.model

import kotlinx.serialization.Serializable

@Serializable
class MealListResponse(
    val meals: List<MealResponse>?,
)


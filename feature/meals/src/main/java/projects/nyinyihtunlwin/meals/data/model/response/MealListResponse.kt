package projects.nyinyihtunlwin.meals.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class MealListResponse(
    val meals: List<MealResponse>?,
)


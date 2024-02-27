package projects.nyinyihtunlwin.cocktails.data.model.response

import kotlinx.serialization.Serializable

@Serializable
class DrinkListResponse(
    val drinks: List<DrinkResponse>?,
)


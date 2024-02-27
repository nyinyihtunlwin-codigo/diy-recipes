package projects.nyinyihtunlwin.cocktails.data.service

import projects.nyinyihtunlwin.cocktails.data.model.response.DrinkListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CocktailsService {
    @GET(URL.API_FILTER)
    suspend fun filter(
        @Query("a") alcoholic: String
    ): Response<DrinkListResponse>

    @GET(URL.API_LOOK_UP)
    suspend fun getDetailsById(
        @Query("i") id: String
    ): Response<DrinkListResponse>
}
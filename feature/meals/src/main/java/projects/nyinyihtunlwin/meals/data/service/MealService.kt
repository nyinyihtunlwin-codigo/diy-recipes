package projects.nyinyihtunlwin.meals.data.service

import projects.nyinyihtunlwin.meals.data.model.response.MealListResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MealService {
    @GET(URL.API_MEAL_CATEGORIES)
    suspend fun getMealCategories():
            Response<MealListResponse>
    @GET(URL.API_FILTER)
    suspend fun filterByCategory(
        @Query("c") category: String
    ): Response<MealListResponse>

    @GET(URL.API_LOOK_UP)
    suspend fun getDetailsById(
        @Query("i") id: String
    ): Response<MealListResponse>
}
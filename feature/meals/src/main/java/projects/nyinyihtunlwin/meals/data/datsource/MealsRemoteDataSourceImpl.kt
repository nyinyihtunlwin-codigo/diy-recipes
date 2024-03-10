package projects.nyinyihtunlwin.meals.data.datsource

import arrow.core.Either
import javax.inject.Inject
import projects.nyinyihtunlwin.common.datastructure.OptionalException
import projects.nyinyihtunlwin.meals.data.mapper.toModel
import projects.nyinyihtunlwin.meals.data.service.MealService
import projects.nyinyihtunlwin.meals.domain.model.MealListData
import projects.nyinyihtunlwin.network.handleCall

internal class MealsRemoteDataSourceImpl @Inject constructor(
    private val mealService: MealService,
) : MealsRemoteDataSource {
    override suspend fun getMealCategories(): Either<OptionalException, MealListData> {
        return handleCall(apiCall = {
            mealService.getMealCategories()
        }, mapper = { data ->
            data.toModel()
        })
    }
}
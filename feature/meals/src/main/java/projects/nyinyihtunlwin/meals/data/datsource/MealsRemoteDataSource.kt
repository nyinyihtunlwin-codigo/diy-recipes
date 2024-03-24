package projects.nyinyihtunlwin.meals.data.datsource

import arrow.core.Either
import projects.nyinyihtunlwin.common.datastructure.OptionalException
import projects.nyinyihtunlwin.meals.domain.model.MealCategoryListData
import projects.nyinyihtunlwin.meals.domain.model.MealListData

interface MealsRemoteDataSource {
    suspend fun getMealCategories(): Either<OptionalException, MealCategoryListData>
    suspend fun getMealsByCategory(category: String): Either<OptionalException, MealListData>
}
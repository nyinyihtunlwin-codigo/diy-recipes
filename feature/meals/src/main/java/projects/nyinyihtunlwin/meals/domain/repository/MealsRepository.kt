package projects.nyinyihtunlwin.meals.domain.repository

import arrow.core.Either
import projects.nyinyihtunlwin.common.datastructure.OptionalException
import projects.nyinyihtunlwin.meals.domain.model.MealCategoryListData
import projects.nyinyihtunlwin.meals.domain.model.MealListData

interface MealsRepository {
    suspend fun getMealCategories(): Either<OptionalException, MealCategoryListData>
    suspend fun getMealsByCategory(category: String): Either<OptionalException, MealListData>
    suspend fun getMealById(id: String): Either<OptionalException, MealListData>
}
package projects.nyinyihtunlwin.meals.domain.repository

import arrow.core.Either
import projects.nyinyihtunlwin.common.datastructure.OptionalException
import projects.nyinyihtunlwin.meals.domain.model.MealCategoryListData

interface MealsRepository {
    suspend fun getMealCategories(): Either<OptionalException, MealCategoryListData>
}
package projects.nyinyihtunlwin.meals.data.repository

import arrow.core.Either
import javax.inject.Inject
import projects.nyinyihtunlwin.common.datastructure.OptionalException
import projects.nyinyihtunlwin.meals.data.datsource.MealsRemoteDataSource
import projects.nyinyihtunlwin.meals.domain.model.MealCategoryListData
import projects.nyinyihtunlwin.meals.domain.model.MealListData
import projects.nyinyihtunlwin.meals.domain.repository.MealsRepository

class MealsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MealsRemoteDataSource
) : MealsRepository {
    override suspend fun getMealCategories(): Either<OptionalException, MealCategoryListData> {
        return remoteDataSource.getMealCategories()
    }

    override suspend fun getMealsByCategory(category: String): Either<OptionalException, MealListData> {
        return remoteDataSource.getMealsByCategory(category)
    }

    override suspend fun getMealById(id: String): Either<OptionalException, MealListData> {
        return remoteDataSource.getMealById(id)
    }
}
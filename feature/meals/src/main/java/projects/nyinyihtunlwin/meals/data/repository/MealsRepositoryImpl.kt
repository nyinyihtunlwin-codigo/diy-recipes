package projects.nyinyihtunlwin.meals.data.repository

import arrow.core.Either
import javax.inject.Inject
import projects.nyinyihtunlwin.common.datastructure.OptionalException
import projects.nyinyihtunlwin.meals.data.datsource.MealsRemoteDataSource
import projects.nyinyihtunlwin.meals.domain.model.MealListData
import projects.nyinyihtunlwin.meals.domain.repository.MealsRepository

class MealsRepositoryImpl @Inject constructor(
    private val remoteDataSource: MealsRemoteDataSource
) : MealsRepository {
    override suspend fun getMealCategories(): Either<OptionalException, MealListData> {
        return remoteDataSource.getMealCategories()
    }
}
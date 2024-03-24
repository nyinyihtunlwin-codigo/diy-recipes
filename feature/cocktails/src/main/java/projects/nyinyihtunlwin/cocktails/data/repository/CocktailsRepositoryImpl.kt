package projects.nyinyihtunlwin.cocktails.data.repository

import arrow.core.Either
import javax.inject.Inject
import projects.nyinyihtunlwin.cocktails.data.datasource.CocktailsRemoteDataSource
import projects.nyinyihtunlwin.cocktails.domain.model.DrinkListData
import projects.nyinyihtunlwin.cocktails.domain.repository.CocktailsRepository
import projects.nyinyihtunlwin.common.datastructure.OptionalException

class CocktailsRepositoryImpl @Inject constructor(
    private val remoteDataSource: CocktailsRemoteDataSource
) : CocktailsRepository {
    override suspend fun getCocktails(isAlcoholic: Boolean): Either<OptionalException, DrinkListData> {
        return remoteDataSource.getCocktails(isAlcoholic)
    }
    override suspend fun getCocktailById(id: String): Either<OptionalException, DrinkListData> {
        return remoteDataSource.getCocktailById(id)
    }
}
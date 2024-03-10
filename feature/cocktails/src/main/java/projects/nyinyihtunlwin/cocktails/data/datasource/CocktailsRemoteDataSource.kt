package projects.nyinyihtunlwin.cocktails.data.datasource

import arrow.core.Either
import projects.nyinyihtunlwin.cocktails.domain.model.DrinkListData
import projects.nyinyihtunlwin.common.datastructure.OptionalException

interface CocktailsRemoteDataSource {
    suspend fun getCocktails(isAlcoholic: Boolean): Either<OptionalException, DrinkListData>
}
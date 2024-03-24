package projects.nyinyihtunlwin.cocktails.domain.repository

import arrow.core.Either
import projects.nyinyihtunlwin.cocktails.domain.model.DrinkListData
import projects.nyinyihtunlwin.common.datastructure.OptionalException

interface CocktailsRepository {
    suspend fun getCocktails(isAlcoholic: Boolean): Either<OptionalException, DrinkListData>
    suspend fun getCocktailById(id: String): Either<OptionalException, DrinkListData>
}
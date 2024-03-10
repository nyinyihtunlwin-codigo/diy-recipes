package projects.nyinyihtunlwin.cocktails.data.datasource

import arrow.core.Either
import javax.inject.Inject
import projects.nyinyihtunlwin.cocktails.data.mapper.toModel
import projects.nyinyihtunlwin.cocktails.data.service.CocktailsService
import projects.nyinyihtunlwin.cocktails.domain.model.DrinkListData
import projects.nyinyihtunlwin.common.datastructure.OptionalException
import projects.nyinyihtunlwin.network.handleCall

internal class CocktailsRemoteDataSourceImpl @Inject constructor(
    private val cocktailsService: CocktailsService,
) : CocktailsRemoteDataSource {
    override suspend fun getCocktails(isAlcoholic: Boolean): Either<OptionalException, DrinkListData> {
        return handleCall(apiCall = {
            cocktailsService.filter(if (isAlcoholic) "Alcoholic" else "Non_Alcoholic")
        }, mapper = { data ->
            data.toModel()
        })
    }
}
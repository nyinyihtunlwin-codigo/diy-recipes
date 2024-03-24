package projects.nyinyihtunlwin.cocktails.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import projects.nyinyihtunlwin.cocktails.domain.model.DrinkListData
import projects.nyinyihtunlwin.cocktails.domain.repository.CocktailsRepository
import projects.nyinyihtunlwin.common.DataException
import projects.nyinyihtunlwin.common.base.SuspendUseCase
import projects.nyinyihtunlwin.common.base.UseCaseParams
import projects.nyinyihtunlwin.common.extension.handle
import projects.nyinyihtunlwin.common.qualifier.IoDispatcher

class GetCocktailByIdUseCase @Inject constructor(
    private val repository: CocktailsRepository,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : SuspendUseCase<GetCocktailByIdUseCase.Params, DrinkListData>() {
    data class Params(
        val id: String
    ) : UseCaseParams

    override suspend fun doWork(
        request: Params,
        onSuccess: (DrinkListData) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit,
    ) {
        repository
            .getCocktailById(id = request.id)
            .handle(
                left = onDataException,
                right = onSuccess,
                terminate = onTerminate
            )
    }
}
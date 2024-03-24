package projects.nyinyihtunlwin.meals.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import projects.nyinyihtunlwin.common.DataException
import projects.nyinyihtunlwin.common.base.SuspendUseCase
import projects.nyinyihtunlwin.common.base.UseCaseParams
import projects.nyinyihtunlwin.common.extension.handle
import projects.nyinyihtunlwin.common.qualifier.IoDispatcher
import projects.nyinyihtunlwin.meals.domain.model.MealListData
import projects.nyinyihtunlwin.meals.domain.repository.MealsRepository

class GetMealByIdUseCase @Inject constructor(
    private val repository: MealsRepository,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : SuspendUseCase<GetMealByIdUseCase.Params, MealListData>() {

    data class Params(
        val id: String,
    ) : UseCaseParams

    override suspend fun doWork(
        request: Params,
        onSuccess: (MealListData) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit,
    ) {
        repository.getMealById(id = request.id)
            .handle(
                left = onDataException,
                right = onSuccess,
                terminate = onTerminate
            )
    }
}
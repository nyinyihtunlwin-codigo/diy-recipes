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

class GetMealListByCategoryUseCase @Inject constructor(
    private val repository: MealsRepository,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : SuspendUseCase<GetMealListByCategoryUseCase.Params, MealListData>() {

    data class Params(
        val category: String,
    ) : UseCaseParams

    override suspend fun doWork(
        request: Params,
        onSuccess: (MealListData) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit,
    ) {
        repository.getMealsByCategory(category = request.category)
            .handle(
                left = onDataException,
                right = onSuccess,
                terminate = onTerminate
            )
    }
}
package projects.nyinyihtunlwin.meals.domain.usecase

import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import projects.nyinyihtunlwin.common.DataException
import projects.nyinyihtunlwin.common.base.EmptyParams
import projects.nyinyihtunlwin.common.base.SuspendUseCase
import projects.nyinyihtunlwin.common.extension.handle
import projects.nyinyihtunlwin.common.qualifier.IoDispatcher
import projects.nyinyihtunlwin.meals.domain.model.MealCategoryListData
import projects.nyinyihtunlwin.meals.domain.repository.MealsRepository

class GetMealCategoryListUseCase @Inject constructor(
    private val repository: MealsRepository,
    @IoDispatcher override val dispatcher: CoroutineDispatcher
) : SuspendUseCase<EmptyParams, MealCategoryListData>() {

    override suspend fun doWork(
        request: EmptyParams,
        onSuccess: (MealCategoryListData) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit,
    ) {
        repository.getMealCategories()
            .handle(
                left = onDataException,
                right = onSuccess,
                terminate = onTerminate
            )
    }
}
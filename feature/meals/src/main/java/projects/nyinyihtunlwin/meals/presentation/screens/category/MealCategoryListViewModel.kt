package projects.nyinyihtunlwin.meals.presentation.screens.category

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import projects.nyinyihtunlwin.common.base.BaseViewModel
import projects.nyinyihtunlwin.common.base.EmptyParams
import projects.nyinyihtunlwin.common.base.UseCaseExecutorProvider
import projects.nyinyihtunlwin.common.event.Event
import projects.nyinyihtunlwin.common.extension.orGenericErrorMsg
import projects.nyinyihtunlwin.meals.domain.usecase.GetMealCategoryListUseCase
import projects.nyinyihtunlwin.meals.presentation.mapper.toUiModel
import projects.nyinyihtunlwin.meals.presentation.model.MealCategoryUiModel

@HiltViewModel
class MealCategoryListViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val getMealCategoryListUseCase: GetMealCategoryListUseCase,
) : BaseViewModel<MealCategoryListUiState, MealCategoryListUiEvent>(
    MealCategoryListUiEvent.Idle,
    MealCategoryListUiState(),
    useCaseExecutorProvider
) {
    init {
        getMealCategories()
    }

    fun getMealCategories() {
        _uiState.update { it.copy(loading = true) }
        execute(
            useCase = getMealCategoryListUseCase,
            value = EmptyParams,
            onSuccess = { data ->
                _uiState.update {
                    it.copy(
                        categories = data.categories.map { it.toUiModel() },
                        loading = false,
                    )
                }
            },
            onDataException = {
                _event.value =
                    Event(MealCategoryListUiEvent.Error(it.message.orGenericErrorMsg()))
            },
            onTerminate = {
                _uiState.update { it.copy(loading = false) }
            }
        )
    }
}


sealed class MealCategoryListUiEvent {
    data object Idle : MealCategoryListUiEvent()
    data class Error(val message: String) : MealCategoryListUiEvent()
}

data class MealCategoryListUiState(
    val loading: Boolean = false,
    val categories: List<MealCategoryUiModel> = emptyList()
)
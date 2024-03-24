package projects.nyinyihtunlwin.meals.presentation.screens.listing

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import projects.nyinyihtunlwin.common.base.BaseViewModel
import projects.nyinyihtunlwin.common.base.UseCaseExecutorProvider
import projects.nyinyihtunlwin.common.event.Event
import projects.nyinyihtunlwin.common.extension.orGenericErrorMsg
import projects.nyinyihtunlwin.meals.domain.usecase.GetMealListByCategoryUseCase
import projects.nyinyihtunlwin.meals.presentation.mapper.toUiModel
import projects.nyinyihtunlwin.meals.presentation.model.MealUiModel

@HiltViewModel
class MealListViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val getMealListByCategoryUseCase: GetMealListByCategoryUseCase,
) : BaseViewModel<MealListUiState, MealListUiEvent>(
    MealListUiEvent.Idle,
    MealListUiState(),
    useCaseExecutorProvider
) {
    fun getMealListByCategory(category: String) {
        _uiState.update { it.copy(loading = true) }
        execute(
            useCase = getMealListByCategoryUseCase,
            value = GetMealListByCategoryUseCase.Params(category = category),
            onSuccess = { data ->
                _uiState.update {
                    it.copy(
                        meals = data.meals.map { it.toUiModel() },
                        loading = false,
                    )
                }
            },
            onDataException = {
                _event.value =
                    Event(MealListUiEvent.Error(it.message.orGenericErrorMsg()))
            },
            onTerminate = {
                _uiState.update { it.copy(loading = false) }
            }
        )
    }
}


sealed class MealListUiEvent {
    data object Idle : MealListUiEvent()
    data class Error(val message: String) : MealListUiEvent()
}

data class MealListUiState(
    val loading: Boolean = false,
    val meals: List<MealUiModel> = emptyList()
)
package projects.nyinyihtunlwin.meals.presentation.screens.details

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import projects.nyinyihtunlwin.common.base.BaseViewModel
import projects.nyinyihtunlwin.common.base.UseCaseExecutorProvider
import projects.nyinyihtunlwin.common.event.Event
import projects.nyinyihtunlwin.common.extension.orGenericErrorMsg
import projects.nyinyihtunlwin.meals.domain.usecase.GetMealByIdUseCase
import projects.nyinyihtunlwin.meals.presentation.mapper.toUiModel
import projects.nyinyihtunlwin.meals.presentation.model.MealUiModel

@HiltViewModel
class MealDetailsViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val getMealByIdUseCase: GetMealByIdUseCase,
) : BaseViewModel<MealDetailsUiState, MealDetailsUiEvent>(
    MealDetailsUiEvent.Idle,
    MealDetailsUiState(),
    useCaseExecutorProvider
) {
    fun getMealDetailsById(id: String) {
        _uiState.update { it.copy(loading = true) }
        execute(
            useCase = getMealByIdUseCase,
            value = GetMealByIdUseCase.Params(id = id),
            onSuccess = { data ->
                _uiState.update {
                    it.copy(
                        meal = data.meals.map { it.toUiModel() }.firstOrNull(),
                        loading = false,
                    )
                }
            },
            onDataException = {
                _event.value =
                    Event(MealDetailsUiEvent.Error(it.message.orGenericErrorMsg()))
            },
            onTerminate = {
                _uiState.update { it.copy(loading = false) }
            }
        )
    }
}


sealed class MealDetailsUiEvent {
    data object Idle : MealDetailsUiEvent()
    data class Error(val message: String) : MealDetailsUiEvent()
}

data class MealDetailsUiState(
    val loading: Boolean = false,
    val meal: MealUiModel? = null
)
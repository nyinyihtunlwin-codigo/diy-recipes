package projects.nyinyihtunlwin.cocktails.presentation.screens.details

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import projects.nyinyihtunlwin.cocktails.domain.usecase.GetCocktailByIdUseCase
import projects.nyinyihtunlwin.cocktails.presentation.mapper.toUiModel
import projects.nyinyihtunlwin.cocktails.presentation.model.DrinkUiModel
import projects.nyinyihtunlwin.common.base.BaseViewModel
import projects.nyinyihtunlwin.common.base.UseCaseExecutorProvider
import projects.nyinyihtunlwin.common.event.Event
import projects.nyinyihtunlwin.common.extension.orGenericErrorMsg

@HiltViewModel
class CocktailDetailsViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val getCocktailByIdUseCase: GetCocktailByIdUseCase,
) : BaseViewModel<CocktailDetailsUiState, CocktailDetailsUiEvent>(
    CocktailDetailsUiEvent.Idle,
    CocktailDetailsUiState(),
    useCaseExecutorProvider
) {
    fun getCocktailDetailsById(id: String) {
        _uiState.update { it.copy(loading = true) }
        execute(
            useCase = getCocktailByIdUseCase,
            value = GetCocktailByIdUseCase.Params(id = id),
            onSuccess = { data ->
                _uiState.update {
                    it.copy(
                        cocktail = data.drinks.map { it.toUiModel() }.firstOrNull(),
                        loading = false,
                    )
                }
            },
            onDataException = {
                _event.value =
                    Event(CocktailDetailsUiEvent.Error(it.message.orGenericErrorMsg()))
            },
            onTerminate = {
                _uiState.update { it.copy(loading = false) }
            }
        )
    }
}


sealed class CocktailDetailsUiEvent {
    data object Idle : CocktailDetailsUiEvent()
    data class Error(val message: String) : CocktailDetailsUiEvent()
}

data class CocktailDetailsUiState(
    val loading: Boolean = false,
    val cocktail: DrinkUiModel? = null
)
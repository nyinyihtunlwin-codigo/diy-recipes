package projects.nyinyihtunlwin.cocktails.presentation.screens.listing

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update
import projects.nyinyihtunlwin.cocktails.domain.usecase.GetCocktailsUseCase
import projects.nyinyihtunlwin.cocktails.presentation.mapper.toUiModel
import projects.nyinyihtunlwin.cocktails.presentation.model.DrinkUiModel
import projects.nyinyihtunlwin.common.base.BaseViewModel
import projects.nyinyihtunlwin.common.base.UseCaseExecutorProvider
import projects.nyinyihtunlwin.common.event.Event
import projects.nyinyihtunlwin.common.extension.orGenericErrorMsg

@HiltViewModel
class CocktailListViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider,
    private val getCocktailsUseCase: GetCocktailsUseCase
) : BaseViewModel<CocktailListUiState, CocktailListUiEvent>(
    CocktailListUiEvent.Idle, CocktailListUiState(), useCaseExecutorProvider
) {
    private fun getCocktails(isAlcoholic: Boolean) {
        _uiState.update { it.copy(loading = true) }
        execute(
            useCase = getCocktailsUseCase,
            value = GetCocktailsUseCase.Params(
                isAlcoholic = isAlcoholic
            ),
            onSuccess = { drinkListData ->
                _uiState.update {
                    it.copy(
                        cocktails = drinkListData.drinks.map { drink -> drink.toUiModel() }
                    )
                }
            },
            onDataException = {
                _event.value =
                    Event(CocktailListUiEvent.Error(it.message.orGenericErrorMsg()))
            },
            onTerminate = {
                _uiState.update { it.copy(loading = false) }
            }
        )
    }
}

sealed class CocktailListUiEvent {
    data object Idle : CocktailListUiEvent()
    data class Error(val message: String) : CocktailListUiEvent()
}

data class CocktailListUiState(
    val loading: Boolean = false,
    val cocktails: List<DrinkUiModel> = emptyList(),
)
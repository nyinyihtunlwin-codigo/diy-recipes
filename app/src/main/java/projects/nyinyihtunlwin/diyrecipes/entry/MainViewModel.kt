package projects.nyinyihtunlwin.diyrecipes.entry

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import projects.nyinyihtunlwin.common.base.BaseViewModel
import projects.nyinyihtunlwin.common.base.UseCaseExecutorProvider

@HiltViewModel
class MainViewModel @Inject constructor(
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<MainUiState, MainUiEvent>(
    MainUiEvent.Idle,
    MainUiState(),
    useCaseExecutorProvider
)

sealed class MainUiEvent {
    data object Idle : MainUiEvent()
    data class Error(val message: String) : MainUiEvent()
}

data class MainUiState(
    val loading: Boolean = false
)
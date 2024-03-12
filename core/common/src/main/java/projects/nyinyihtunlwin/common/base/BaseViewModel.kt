package projects.nyinyihtunlwin.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import projects.nyinyihtunlwin.common.DataException
import projects.nyinyihtunlwin.common.event.Event
import projects.nyinyihtunlwin.common.event.GlobalEvent

abstract class BaseViewModel<S, T> (
    initialEvent: T,
    initialState: S,
    useCaseExecutorProvider: UseCaseExecutorProvider,
) : ViewModel() {

    @Inject lateinit var globalEvent: GlobalEvent

    protected val _uiState = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()


    val _event: MutableStateFlow<Event<T>> = MutableStateFlow(Event(initialEvent))
    val event: StateFlow<Event<T>> = _event.asStateFlow()

    private val useCaseExecutor by lazy {
        useCaseExecutorProvider(viewModelScope)
    }

    protected fun <OUTPUT> execute(
        useCase: UseCase<EmptyParams, OUTPUT>,
        onSuccess: (OUTPUT) -> Unit = {},
        onDataException: (DataException) -> Unit = {},
        onTerminate: () -> Unit = {},
    ) {
        execute(useCase, EmptyParams, onSuccess, onDataException, onTerminate)
    }

    protected fun <INPUT : UseCaseParams, OUTPUT> execute(
        useCase: UseCase<INPUT, OUTPUT>,
        value: INPUT,
        onSuccess: (OUTPUT) -> Unit = {},
        onDataException: (DataException) -> Unit = {},
        onTerminate: () -> Unit = {},
    ) {
        with(VMExecutor<INPUT, OUTPUT>()) {
            executeUseCase(
                useCase = useCase,
                value = value,
                onSuccess = onSuccess,
                onDataException = onDataException,
                onTerminate = onTerminate,
                useCaseExecutor = useCaseExecutor
            )
        }
    }

    class VMExecutor<INPUT : UseCaseParams, OUTPUT> {
        fun executeUseCase(
            useCase: UseCase<INPUT, OUTPUT>,
            value: INPUT,
            onSuccess: (OUTPUT) -> Unit = {},
            onDataException: (DataException) -> Unit = {},
            onTerminate: () -> Unit = {},
            useCaseExecutor: UseCaseExecutor,
        ) {
            useCaseExecutor.execute(useCase, value, onSuccess, onDataException, onTerminate)
        }
    }


}

//Base -> UseExt

//Child -> exe()

//Base ->  VMExc -> UseExt(VMExc)


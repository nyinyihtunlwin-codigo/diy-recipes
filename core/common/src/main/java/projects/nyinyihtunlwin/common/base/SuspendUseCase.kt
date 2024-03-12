package projects.nyinyihtunlwin.common.base

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import projects.nyinyihtunlwin.common.DataException

typealias UseCaseExecutorProvider =
        @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor

interface UseCase<REQUEST : UseCaseParams, RESULT> {

    context (BaseViewModel.VMExecutor<REQUEST, RESULT>)
    suspend fun execute(
        input: REQUEST,
        onSuccess: (RESULT) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit,
    )
}

abstract class SuspendUseCase<REQUEST : UseCaseParams, RESULT> : UseCase<REQUEST, RESULT> {
    abstract val dispatcher: CoroutineDispatcher

    context (BaseViewModel.VMExecutor<REQUEST, RESULT>)
    override suspend fun execute(
        input: REQUEST,
        onSuccess: (RESULT) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit,
    ) {
        withContext(dispatcher) {
            doWork(
                request = input,
                onSuccess = onSuccess,
                onDataException = onDataException,
                onTerminate = onTerminate
            )
        }
    }

    abstract suspend fun doWork(
        request: REQUEST,
        onSuccess: (RESULT) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit
    )
}


abstract class DelegateSuspendUseCase<REQUEST : UseCaseParams, RESULT> : UseCase<REQUEST, RESULT> {
    abstract val dispatcher: CoroutineDispatcher


    override suspend fun execute(
        input: REQUEST,
        onSuccess: (RESULT) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit,
    ) {
        withContext(dispatcher) {
            doWork(
                request = input,
                onSuccess = onSuccess,
                onDataException = onDataException,
                onTerminate = onTerminate
            )
        }
    }

    abstract suspend fun doWork(
        request: REQUEST,
        onSuccess: (RESULT) -> Unit,
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit
    )
}

class UseCaseExecutor(
    private val coroutineScope: CoroutineScope
) {
    context (BaseViewModel.VMExecutor<REQUEST, RESULT>)
    fun <REQUEST : UseCaseParams, RESULT> execute(
        useCase: UseCase<REQUEST, RESULT>,
        value: REQUEST,
        onSuccess: (RESULT) -> Unit = {},
        onDataException: (DataException) -> Unit,
        onTerminate: () -> Unit
    ) {
        coroutineScope.launch {
            try {
                useCase.execute(value, onSuccess, onDataException, onTerminate)
            } catch (ignore: CancellationException) {
            }
        }
    }
}


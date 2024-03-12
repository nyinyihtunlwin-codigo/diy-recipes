package projects.nyinyihtunlwin.common.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<REQUEST: UseCaseParams, RESULT> {
    abstract val dispatcher: CoroutineDispatcher
    abstract fun createFlow(input: REQUEST): Flow<RESULT>
    fun buildFlow(input: REQUEST): Flow<RESULT> = createFlow(input).flowOn(dispatcher)
}
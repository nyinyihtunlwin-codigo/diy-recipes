package projects.nyinyihtunlwin.common.event

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

interface GlobalEvent {
    val event: SharedFlow<Event>

    fun emit(event: Event)

    sealed interface Event {
        data object Idle : Event

        data class Success(
            val msg: String,
            val delay: Long
        ) : Event

        data class Error(
            val title: String,
            val msg: String,
        ) : Event
    }
}

class GlobalEventImpl : GlobalEvent {
    private val _event = MutableSharedFlow<GlobalEvent.Event>()
    override val event: SharedFlow<GlobalEvent.Event>
        get() = _event.asSharedFlow()

    private var emitJob: Job? = null

    @OptIn(DelicateCoroutinesApi::class)
    override fun emit(event: GlobalEvent.Event) {
        emitJob?.cancel()
        emitJob = GlobalScope.launch(Dispatchers.IO) {
            _event.emit(event)
        }
    }
}
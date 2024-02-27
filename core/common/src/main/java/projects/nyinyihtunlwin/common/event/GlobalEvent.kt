package projects.nyinyihtunlwin.common.event

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

interface GlobalEvent {
    val event: SharedFlow<Event>

    fun emit(event: Event)

    fun emit(statusCode: Int, body: String)

    sealed interface Event {
        object Idle : Event

        data class UnAuthorize(
            val msg: String,
        ) : Event

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

    @OptIn(DelicateCoroutinesApi::class)
    override fun emit(statusCode: Int, body: String) {
        emitJob?.cancel()
        emitJob = GlobalScope.launch(Dispatchers.IO) {
            try {
                JSONObject(body)
            } catch (e: Throwable) {
                null
            }?.let { jsonObject ->
                val responseCode = try {
                    jsonObject.getInt("code")
                } catch (e: Throwable) {
                    null
                }
                val responseMessage = try {
                    jsonObject.getString("message")
                } catch (e: Throwable) {
                    null
                }
                val errorCode = try {
                    jsonObject.getInt("error_code")
                } catch (e: Throwable) {
                    null
                }
                val errorMessage = try {
                    jsonObject.getString("error_msg")
                } catch (e: Throwable) {
                    null
                }

                val code = responseCode ?: errorCode ?: statusCode
                val message =
                    responseMessage ?: errorMessage ?: "Something went wrong. Please try again."

                when (code) {
                    401 -> {
                        _event.emit(GlobalEvent.Event.UnAuthorize(message))
                    }

                    else -> {
                        _event.emit(
                            GlobalEvent.Event.Error(
                                msg = message,
                                title = "Error",
                            )
                        )
                    }
                }
            }
        }
    }
}
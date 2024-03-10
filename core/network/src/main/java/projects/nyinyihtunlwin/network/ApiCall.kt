package projects.nyinyihtunlwin.network

import android.accounts.NetworkErrorException
import arrow.core.Either
import arrow.core.Option
import arrow.core.some
import kotlinx.serialization.SerializationException
import retrofit2.Response
import timber.log.Timber
import java.net.UnknownHostException
import projects.nyinyihtunlwin.common.DataException
import projects.nyinyihtunlwin.common.datastructure.OptionalException


const val ERROR_MESSAGE_GENERAL = "Something went wrong. Please try again."
const val ERROR_JSON_CONVERSION = "Error json conversion. Please try again."
const val ERROR_TITLE_GENERAL = "Error"

suspend fun <T, R> handleCall(
    apiCall: suspend () -> Response<T>,
    mapper: suspend (T?) -> R
): Either<OptionalException, R> = try {
    val response = apiCall.invoke()
    if (response.isSuccessful) {
        Either.Right(mapper(response.body()))
    } else {
        Either.Left(
            DataException.Api(
                title = ERROR_TITLE_GENERAL,
                message = response.errorBody().toString(),
                errorCode = response.code()
            ).some()
        )
    }
} catch (e: Exception) {
    Timber.tag("CPACK").i("Reach $e")
    e.printStackTrace()
    e.convertEither()
}

internal fun Exception.convertEither(): Either<Option<DataException>, Nothing> = when (this) {
    is NetworkErrorException, is UnknownHostException -> Either.Left(DataException.Network.some())
    is SerializationException -> {
        Either.Left(
            DataException.Api(
                message = this.message ?: ERROR_JSON_CONVERSION,
                title = ERROR_TITLE_GENERAL,
                errorCode = -1
            ).some()
        )
    }

    else -> Either.Left(
        DataException.Api(
            message = this.message ?: ERROR_MESSAGE_GENERAL,
            title = ERROR_TITLE_GENERAL,
            errorCode = -1
        ).some()
    )
}
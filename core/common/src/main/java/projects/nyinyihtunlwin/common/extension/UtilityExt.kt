package projects.nyinyihtunlwin.common.extension

import arrow.core.Either
import arrow.core.Some
import projects.nyinyihtunlwin.common.DataException
import projects.nyinyihtunlwin.common.datastructure.OptionalException

fun <A : OptionalException, B> Either<A, B>.handle(
    left: (DataException) -> Unit,
    right: (B) -> Unit,
    terminate: () -> Unit
) {
    this.tapLeft {
        it.handleError(left)
        terminate()
    }.tap {
        right.invoke(it)
        terminate()
    }
}

fun OptionalException.handleError(
    left: (DataException) -> Unit
) {
    (this as? OptionalException)?.let { option ->
        if (option is Some) {
            left(option.value)
        }
    }
}
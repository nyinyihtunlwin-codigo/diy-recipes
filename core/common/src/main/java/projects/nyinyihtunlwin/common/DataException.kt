package projects.nyinyihtunlwin.common

sealed class DataException : RuntimeException {

    constructor() : super()

    constructor(message: String) : super(message)

    object Network : DataException("Unable to connect. Please check connection.")

    object Conversion : DataException()

    object SessionExpired : DataException()

    data class Custom(val msg: String) : DataException()

    data class NewUser(val otpToken: String) : DataException()

    object PaymentError : DataException()

    data class Api(
        override val message: String,
        val title: String = "",
        val errorCode: Int = -1
    ) : DataException(message)
}
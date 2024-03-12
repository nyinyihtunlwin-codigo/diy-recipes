package projects.nyinyihtunlwin.common.extension

fun String.ifEmptyNull() = this.ifEmpty { null }
fun String?.orGenericErrorMsg(): String = this ?: "Something went wrong"

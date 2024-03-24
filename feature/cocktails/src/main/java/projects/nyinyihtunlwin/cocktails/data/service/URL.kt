package projects.nyinyihtunlwin.cocktails.data.service

object URL {
    const val PATH = "/api/json"
    const val VERSION = "/v1"
    const val TEMP_KEY = "/1"

    const val METHOD_FILTER = "/filter.php"
    const val METHOD_LOOK_UP= "/lookup.php"

    const val API_FILTER = PATH + VERSION + TEMP_KEY + METHOD_FILTER
    const val API_LOOK_UP= PATH + VERSION + TEMP_KEY + METHOD_LOOK_UP
}
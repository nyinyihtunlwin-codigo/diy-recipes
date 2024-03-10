package projects.nyinyihtunlwin.network

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import projects.nyinyihtunlwin.common.event.GlobalEvent

class HttpInterceptor @Inject constructor(
    private val globalEvent: GlobalEvent
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalResponse = chain.proceed(originalRequest)

        val statusCode = originalResponse.code
        val body = originalResponse.body

        val bodyStr = body?.string().orEmpty()
        if (statusCode != 200) {
            globalEvent.emit(
                GlobalEvent.Event.Error("Error", bodyStr)
            )
        }
        return originalResponse.newBuilder()
            .body(bodyStr.toResponseBody(originalResponse.body?.contentType())).build()
    }
}

class NetworkInterceptor @Inject constructor(
    @ApplicationContext private val context: Context, private val globalEvent: GlobalEvent
) : Interceptor {

    private var isConnected = true

    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected() && isConnected) {
            isConnected = false
            globalEvent.emit(
                GlobalEvent.Event.Error("NetworkError", "No internet connection.")
            )
        } else {
            isConnected = true
        }

        val builder: Request.Builder = chain.request().newBuilder()
        return chain.proceed(builder.build())
    }

    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        if (connectivityManager != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                val capabilities: NetworkCapabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                        ?: return false
                return capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || capabilities.hasTransport(
                    NetworkCapabilities.TRANSPORT_WIFI
                ) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
            } else {
                return try {
                    val activeNetworkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
                    activeNetworkInfo != null && activeNetworkInfo.isConnected
                } catch (e: Exception) {
                    false
                }
            }
        } else {
            return false
        }
    }
}
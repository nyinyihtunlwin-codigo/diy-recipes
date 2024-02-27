package projects.nyinyihtunlwin.network

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.moczul.ok2curl.CurlInterceptor
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import timber.log.Timber

object RetrofitInitializer {
    @OptIn(ExperimentalSerializationApi::class)
    fun providesNetworkJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
        encodeDefaults = true
        isLenient = true
        prettyPrint = true
    }

    fun createOkHttpClient(
        context: Context,
        interceptors: List<Interceptor>,
    ) = OkHttpClient.Builder()
        .addInterceptor(ChuckerInterceptor.Builder(context).build())
        .also {
            interceptors.forEach { interceptor ->
                it.addInterceptor(interceptor)
            }
            if (BuildConfig.DEBUG) {
                it.addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }

            if ( BuildConfig.DEBUG) {
                it.addInterceptor(CurlInterceptor { curl -> Timber.tag("CURL").d(curl) })
            }
        }
        .readTimeout(60L, TimeUnit.SECONDS)
        .writeTimeout(60L, TimeUnit.SECONDS)
        .build()

    fun createRetrofitClient(url: String, okHttpClient: OkHttpClient, networkJson: Json):
            Retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(NullOnEmptyConverterFactory())
        .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
        .build()

    private class NullOnEmptyConverterFactory : Converter.Factory() {
        override fun responseBodyConverter(
            type: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<ResponseBody, *> {
            val delegate: Converter<ResponseBody, *> =
                retrofit.nextResponseBodyConverter<Any>(this, type, annotations)
            return Converter { body: ResponseBody ->
                if (body.contentLength() == 0L) {
                    null
                } else {
                    delegate.convert(body)
                }
            }
        }
    }
}
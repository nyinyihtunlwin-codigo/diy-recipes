package projects.nyinyihtunlwin.network

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton
import projects.nyinyihtunlwin.network.RetrofitInitializer.createOkHttpClient
import projects.nyinyihtunlwin.network.RetrofitInitializer.createRetrofitClient

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun providesNetworkJson(): Json = RetrofitInitializer.providesNetworkJson()

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        httpInterceptor: HttpInterceptor,
        networkInterceptor: NetworkInterceptor,
    ): OkHttpClient {

        return createOkHttpClient(
            context = context,
            interceptors = listOf(
                httpInterceptor,
                networkInterceptor
            )
        )
    }

    @Provides
    @Singleton
    @Named("meals")
    fun provideRetrofitForMeals(okHttpClient: OkHttpClient, networkJson: Json): Retrofit {
        return createRetrofitClient(
            BuildConfig.BASE_MEAL_URL,
            okHttpClient,
            networkJson
        )
    }

    @Provides
    @Singleton
    @Named("cocktails")
    fun provideRetrofitForCocktails(okHttpClient: OkHttpClient, networkJson: Json): Retrofit {
        return createRetrofitClient(
            BuildConfig.BASE_COCKTAILS_URL,
            okHttpClient,
            networkJson
        )
    }
}


package projects.nyinyihtunlwin.cocktails.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import projects.nyinyihtunlwin.cocktails.data.service.CocktailsService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideCocktailsService(
        @Named("cocktails") retrofit: Retrofit
    ): CocktailsService = retrofit.create(CocktailsService::class.java)
}
package projects.nyinyihtunlwin.meals.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.meals.data.service.MealService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideMealsService(
        retrofit: Retrofit
    ): MealService = retrofit.create(MealService::class.java)
}
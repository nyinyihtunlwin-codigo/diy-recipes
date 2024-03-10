package projects.nyinyihtunlwin.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.cocktails.data.service.CocktailsService
import projects.nyinyihtunlwin.meals.data.service.MealService
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideCocktailsService(retrofit: Retrofit): projects.nyinyihtunlwin.cocktails.data.service.CocktailsService {
        return retrofit.create(projects.nyinyihtunlwin.cocktails.data.service.CocktailsService::class.java)
    }

    @Provides
    @Singleton
    fun provideMealsService(retrofit: Retrofit): projects.nyinyihtunlwin.meals.data.service.MealService {
        return retrofit.create(projects.nyinyihtunlwin.meals.data.service.MealService::class.java)
    }

}

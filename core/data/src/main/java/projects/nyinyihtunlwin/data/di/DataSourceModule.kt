package projects.nyinyihtunlwin.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.cocktails.data.datasource.CocktailsRemoteDataSource
import projects.nyinyihtunlwin.cocktails.data.datasource.CocktailsRemoteDataSourceImpl
import projects.nyinyihtunlwin.meals.data.datsource.MealsRemoteDataSource
import projects.nyinyihtunlwin.meals.data.datsource.MealsRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    internal abstract fun bindMealsRemoteDataSource(
        mealsRemoteDataSource: projects.nyinyihtunlwin.meals.data.datsource.MealsRemoteDataSourceImpl
    ): projects.nyinyihtunlwin.meals.data.datsource.MealsRemoteDataSource
    @Binds
    @Singleton
    internal abstract fun bindCocktailsRemoteDataSource(
        cocktailsRemoteDataSource: projects.nyinyihtunlwin.cocktails.data.datasource.CocktailsRemoteDataSourceImpl
    ): projects.nyinyihtunlwin.cocktails.data.datasource.CocktailsRemoteDataSource
}
package projects.nyinyihtunlwin.cocktails.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.cocktails.data.datasource.CocktailsRemoteDataSource
import projects.nyinyihtunlwin.cocktails.data.datasource.CocktailsRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    internal abstract fun bindCocktailsRemoteDataSource(
        remoteDataSource: CocktailsRemoteDataSourceImpl
    ): CocktailsRemoteDataSource
}
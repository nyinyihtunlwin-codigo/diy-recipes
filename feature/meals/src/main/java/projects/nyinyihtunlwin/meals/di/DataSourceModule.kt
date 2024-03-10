package projects.nyinyihtunlwin.meals.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.meals.data.datsource.MealsRemoteDataSource
import projects.nyinyihtunlwin.meals.data.datsource.MealsRemoteDataSourceImpl

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    internal abstract fun bindMealsRemoteDataSource(
        remoteDataSource: MealsRemoteDataSourceImpl
    ): MealsRemoteDataSource
}
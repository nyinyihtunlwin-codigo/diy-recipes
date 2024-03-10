package projects.nyinyihtunlwin.meals.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.meals.data.repository.MealsRepositoryImpl
import projects.nyinyihtunlwin.meals.domain.repository.MealsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun bindMealsRepository(
        mealsRepository: MealsRepositoryImpl
    ): MealsRepository
}
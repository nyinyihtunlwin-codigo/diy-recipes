package projects.nyinyihtunlwin.cocktails.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.cocktails.data.repository.CocktailsRepositoryImpl
import projects.nyinyihtunlwin.cocktails.domain.repository.CocktailsRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    internal abstract fun bindCocktailsRepository(
        cocktailsRepository: CocktailsRepositoryImpl
    ): CocktailsRepository
}
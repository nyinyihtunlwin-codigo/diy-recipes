package projects.nyinyihtunlwin.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import projects.nyinyihtunlwin.database.DiyRecipesDatabase
import projects.nyinyihtunlwin.database.dao.DrinksDao
import projects.nyinyihtunlwin.database.dao.MealsDao

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDiyRecipesDatabase(@ApplicationContext context: Context): DiyRecipesDatabase =
        Room
            .databaseBuilder(context, DiyRecipesDatabase::class.java, "diy_recipes_database")
            .build()

    @Singleton
    @Provides
    fun provideMealsDao(database: DiyRecipesDatabase): MealsDao = database.getMealsDao()

    @Singleton
    @Provides
    fun provideDrinksDao(database: DiyRecipesDatabase): DrinksDao = database.getDrinksDao()

}
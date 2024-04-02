package projects.nyinyihtunlwin.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import projects.nyinyihtunlwin.database.converter.Converters
import projects.nyinyihtunlwin.database.dao.DrinksDao
import projects.nyinyihtunlwin.database.dao.MealsDao
import projects.nyinyihtunlwin.database.entity.DrinkEntity
import projects.nyinyihtunlwin.database.entity.MealEntity
@TypeConverters(Converters::class)
@Database(
    entities = [MealEntity::class, DrinkEntity::class],
    exportSchema = false,
    version = 1,
)
abstract class DiyRecipesDatabase : RoomDatabase() {
    abstract fun getMealsDao(): MealsDao
    abstract fun getDrinksDao(): DrinksDao
}
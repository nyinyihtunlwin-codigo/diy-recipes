package projects.nyinyihtunlwin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import projects.nyinyihtunlwin.database.entity.MealEntity

@Dao
interface MealsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllMeals(meals: List<MealEntity>)

    @Query("SELECT * FROM meals")
    suspend fun getAllMeals(): List<MealEntity>

    @Query("SELECT * FROM meals WHERE strCategory is :category")
    fun getMealsByCategory(category: String): List<MealEntity>

    @Query("SELECT * FROM meals WHERE idMeal is :id")
    fun getMealById(id: String): MealEntity
}
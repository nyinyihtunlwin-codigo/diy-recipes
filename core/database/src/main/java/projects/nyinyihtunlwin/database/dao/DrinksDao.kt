package projects.nyinyihtunlwin.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import projects.nyinyihtunlwin.database.entity.DrinkEntity

@Dao
interface DrinksDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDrinks(drinks: List<DrinkEntity>)

    @Query("SELECT * FROM drinks")
    suspend fun getAllDrinks(): List<DrinkEntity>

    @Query("SELECT * FROM drinks WHERE strAlcoholic is :alcoholic")
    fun getDrinks(alcoholic: String): List<DrinkEntity>

    @Query("SELECT * FROM drinks WHERE idDrink is :id")
    fun getDrinkById(id: String): DrinkEntity
}
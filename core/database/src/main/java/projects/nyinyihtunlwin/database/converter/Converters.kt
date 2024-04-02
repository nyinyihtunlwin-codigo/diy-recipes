package projects.nyinyihtunlwin.database.converter

import android.util.Log
import androidx.room.TypeConverter
import timber.log.Timber

class Converters {
    @TypeConverter
    fun fromListToString(stringList: List<String>): String = stringList.toString()

    @TypeConverter
    fun toListFromString(stringList: String): List<String> {
        val result = ArrayList<String>()
        val split = stringList.replace("[", "").replace("]", "").replace(" ", "").split(",")
        for (n in split) {
            try {
                result.add(n)
            } catch (e: Exception) {
                Timber.log(Log.ERROR, e.toString())
            }
        }
        return result
    }
}
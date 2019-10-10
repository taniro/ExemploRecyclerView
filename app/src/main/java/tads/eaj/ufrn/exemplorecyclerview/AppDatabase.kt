package tads.eaj.ufrn.exemplorecyclerview

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Fruta::class), version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun frutaDao():FrutaDao
}
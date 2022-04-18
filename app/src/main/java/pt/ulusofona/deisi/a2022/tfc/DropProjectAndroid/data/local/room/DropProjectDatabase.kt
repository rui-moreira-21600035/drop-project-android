package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.dao.AssignmentDao
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment

@Database(entities = arrayOf(Assignment::class), version = 1, exportSchema = false)
abstract class DropProjectDatabase: RoomDatabase() {

    abstract fun assignmentDao(): AssignmentDao

    companion object {

        private var instance: DropProjectDatabase? = null

        fun getInstance(applicationContext: Context): DropProjectDatabase {
            synchronized(this) {
                if(instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext,
                        DropProjectDatabase::class.java,
                        "drop_project_db").build()

                }
                return instance as DropProjectDatabase
            }
        }
    }
}
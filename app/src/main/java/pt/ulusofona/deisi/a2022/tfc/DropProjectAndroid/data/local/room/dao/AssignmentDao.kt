package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.dao

import androidx.room.*
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment

@Dao
interface AssignmentDao {

    @Insert
    suspend fun insert(assignment: Assignment)

    @Query("SELECT * FROM assignments")
    suspend fun getAllAssignments(): List<Assignment>

    @Update
    suspend fun updateAssignment(assignment: Assignment)

    @Query("SELECT * FROM assignments WHERE id = :id")
    suspend fun getById(id: String): List<Assignment>

    @Query("DELETE FROM assignments WHERE id = :id")
    suspend fun deleteById(id: String)
}
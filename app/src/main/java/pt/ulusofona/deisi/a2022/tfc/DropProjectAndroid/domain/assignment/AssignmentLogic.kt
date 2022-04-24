package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.domain.assignment

import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.dao.AssignmentDao
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.repositories.AssignmentRepository
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentsLoaded
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnDatabaseEntry

class AssignmentLogic(private val repository: AssignmentRepository) {

    fun getAssignmentsList(listener: OnAssignmentsLoaded?){
        repository.getAllAssignments(listener)
    }

    fun deleteAssignmentById(id: String, listener: OnAssignmentsLoaded?){
        repository.deleteAssignmentById(id, listener)
    }

    fun insertAssignment(assignment: Assignment, listener:OnDatabaseEntry){
        repository.insertAssignment(assignment, listener)
    }
}
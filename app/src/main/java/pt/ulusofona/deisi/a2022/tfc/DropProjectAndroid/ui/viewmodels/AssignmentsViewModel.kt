package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.DropProjectDatabase
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.repositories.AssignmentRepository
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.domain.assignment.AssignmentLogic
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentsLoaded
import java.util.ArrayList

class AssignmentsViewModel(application: Application) : AndroidViewModel(application) {
    // TODO: Implement ViewModel
    // TODO: Implement Logic
    // TODO: Implement Interfaces
    private var listener: OnAssignmentsLoaded? = null
    private val localData = DropProjectDatabase.getInstance(application).assignmentDao()
//    private val remoteData =
    private val repository = AssignmentRepository(localData)
    private val assignmentLogic = AssignmentLogic(repository)

    fun loadAssignments(){
        assignmentLogic.getAssignmentsList(listener)
    }

    fun registerListener(onAssignmentsLoaded: OnAssignmentsLoaded){
        listener = onAssignmentsLoaded
    }

    fun unregisterListener(){
        listener = null
    }
}
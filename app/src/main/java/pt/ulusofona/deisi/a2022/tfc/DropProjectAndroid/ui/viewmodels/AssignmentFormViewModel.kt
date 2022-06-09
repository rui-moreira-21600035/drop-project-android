package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels

import android.app.Application
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.lifecycle.AndroidViewModel
import com.google.android.material.textfield.TextInputEditText
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.DropProjectDatabase
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.repositories.AssignmentRepository
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.domain.assignment.AssignmentLogic
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentFetched
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentsLoaded
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnDataChanged
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnDatabaseEntry

class AssignmentFormViewModel(application: Application) : AndroidViewModel(application) {

    private val localData = DropProjectDatabase.getInstance(application).assignmentDao()
    private val repository = AssignmentRepository(localData)

    private var assignmentLogic = AssignmentLogic(repository)

    private var listener : OnAssignmentFetched? = null

    /** args **/
    var assignId: String = ""

    fun insertEntryDB(assignment: Assignment, listener: OnDatabaseEntry){
        assignmentLogic.insertAssignment(assignment, listener)
    }

    fun updateEntryDB(assignment: Assignment, listener: OnDatabaseEntry){
        assignmentLogic.updateAssignment(assignment, listener)
    }

    fun getAssignmentById(id: String, listener: OnAssignmentFetched){
        repository.getAssignmentById(id, listener)
    }
}
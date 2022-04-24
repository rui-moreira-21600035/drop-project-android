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
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentsLoaded
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnDatabaseEntry

class AssignmentFormViewModel(application: Application) : AndroidViewModel(application) {
    private var listener: OnAssignmentsLoaded? = null

    private val localData = DropProjectDatabase.getInstance(application).assignmentDao()
    private val repository = AssignmentRepository(localData)

    private var assignmentLogic = AssignmentLogic(repository)

    /** args **/
    var assignId: String? = null
    var assignName: String? = null
    var assignTags: String? = null
    var assignPackage: String? = null
    var assignGit: String? = null
    var assignProgLang: String? = null
    var assignLeaderType: String? = null

    fun insertEntryDB(assignment: Assignment, listener: OnDatabaseEntry){
        assignmentLogic.insertAssignment(assignment, listener)
    }
}
package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.repositories

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.dao.AssignmentDao
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentFetched
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentsLoaded
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnDatabaseEntry
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

@Suppress("DEPRECATION")
class AssignmentRepository(private val localData: AssignmentDao/*, private val remoteData: RemoteData*/) {

//    fun isNetworkAvailable(): Boolean {
//        val connectivityManager =
//            remoteData.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        return connectivityManager.activeNetworkInfo?.isConnected ?: false
//    }

    fun getAllAssignments(listener: OnAssignmentsLoaded?){
        CoroutineScope(Dispatchers.IO).launch {
            var list: ArrayList<Assignment> = ArrayList(localData.getAllAssignments())
            if (list.isEmpty()){
                /* TODO: Retirar este excerto de código a seguir, antes de testar retrofit */
                populateDB() // Popula a BD para testes
                list = ArrayList(localData.getAllAssignments())
                listener!!.onAssignmentsLoaded(list)
            } else {
                listener!!.onAssignmentsLoaded(list)
            }
        }
    }

    fun populateDB(){
        CoroutineScope(Dispatchers.IO).launch {
            localData.insertAssignment(Assignment("assignment1", "Assignment #1", "cs,programming",
                "Computação Móvel", "edu.someUniversity.cs1.Assignment1",
                "git@github.com:someuser/cs1Assignment1.git", "Kotlin",
                "0",getCurrentDateAndTime(), ""))
            localData.insertAssignment(Assignment("assignment2", "Assignment #2", "cs,programming",
                "Linguagens Programação I", "edu.someUniversity.cs1.Assignment2",
                "git@github.com:someuser/cs1Assignment2.git", "Java",
                "0",getCurrentDateAndTime(), ""))
            localData.insertAssignment(Assignment("assignment3", "Assignment #3", "cs,programming",
                "Algoritmia e Estruturas de Dados", "edu.someUniversity.cs1.Assignment3",
                "git@github.com:someuser/cs1Assignment3.git", "Java",
                "0",getCurrentDateAndTime(), ""))
            localData.insertAssignment(Assignment("assignment4", "Assignment #4", "cs,programming",
                "Linguagens Programação II", "edu.someUniversity.cs1.Assignment4",
                "git@github.com:someuser/cs1Assignment4.git", "Java",
                "0",getCurrentDateAndTime(), ""))
        }
    }

    fun getAssignmentById(id: String, listener: OnAssignmentFetched?){
        CoroutineScope(Dispatchers.IO).launch {
            val assignment = localData.getById(id)
            listener!!.fillTextInputs(assignment)
        }
    }

    fun insertAssignment(assignment: Assignment, listener: OnDatabaseEntry?){
        CoroutineScope(Dispatchers.IO).launch {
            localData.insertAssignment(assignment)
            listener!!.onDBEntry()
        }
    }

    fun updateAssignment(assignment: Assignment, listener: OnDatabaseEntry?){
        CoroutineScope(Dispatchers.IO).launch {
            localData.updateAssignment(assignment)
            listener!!.onDBEntry()
        }
    }

    fun deleteAssignmentById(id: String, listener: OnAssignmentsLoaded?){
        CoroutineScope(Dispatchers.IO).launch {
            localData.deleteById(id)
            getAllAssignments(listener)
        }
    }

    fun getCurrentDateAndTime(): String{
        return SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date())

    }

}
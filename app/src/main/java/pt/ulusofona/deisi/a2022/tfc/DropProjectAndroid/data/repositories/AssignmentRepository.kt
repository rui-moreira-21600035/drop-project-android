package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.repositories

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.dao.AssignmentDao
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.remote.RemoteData
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentsLoaded

@Suppress("DEPRECATION")
class AssignmentRepository(private val localData: AssignmentDao/*, private val remoteData: RemoteData*/) {


//    fun isNetworkAvailable(): Boolean {
//        val connectivityManager =
//            remoteData.application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//        return connectivityManager.activeNetworkInfo?.isConnected ?: false
//    }

    fun getAllAssignments(listener: OnAssignmentsLoaded?){
        CoroutineScope(Dispatchers.IO).launch {
            val list: ArrayList<Assignment> = ArrayList(localData.getAllAssignments())
            if (list.isEmpty()){
                /* TODO: Retirar este excerto de código a seguir, antes de testar retrofit */
                list.add(Assignment("assignment1", "Assignment #1", "cs,programming",
                    "Computação Móvel", "edu.someUniversity.cs1.Assignment1",
                    "git@github.com:someuser/cs1Assignment1.git", "Kotlin",
                    0,"15/04/2022 00:10:00" ,""))
                list.add(Assignment("assignment2", "Assignment #2", "cs,programming",
                    "Linguagens Programação I", "edu.someUniversity.cs1.Assignment2",
                    "git@github.com:someuser/cs1Assignment1.git", "Java",
                    0,"15/04/2022 00:10:00" ,""))
            }
            listener!!.onAssignmentsLoaded(list)
        }
    }

}
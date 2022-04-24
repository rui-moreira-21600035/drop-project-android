package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.dummy

import android.util.Log
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment

object AssignmentList {
    // TODO: Eliminar: Classe criada para efeitos de teste
    val assignmentList: ArrayList<Assignment> = ArrayList()

    init {
//        assignmentList.add(
//            Assignment("assignment1", "Assignment #1", "cs,programming",
//            "Computação Móvel", "edu.someUniversity.cs1.Assignment1",
//            "git@github.com:someuser/cs1Assignment1.git", "Kotlin",
//            0,"15/04/2022 00:10:00" ,"")
//        )
//
//        assignmentList.add(
//            Assignment("assignment2", "Assignment #2", "cs,programming",
//            "Linguagens Programação I", "edu.someUniversity.cs1.Assignment2",
//            "git@github.com:someuser/cs1Assignment1.git", "Java",
//            0,"15/04/2022 00:10:00" ,"")
//        )
//
//        assignmentList.add(
//            Assignment("assignment3", "Assignment #3", "cs,programming",
//            "Computação Móvel", "edu.someUniversity.cs1.Assignment3",
//            "git@github.com:someuser/cs1Assignment1.git", "Kotlin",
//            0, "15/04/2022 00:10:00" ,"")
//        )
//
//        assignmentList.add(
//            Assignment("assignment4", "Assignment #4", "cs,programming",
//            "Linguagens Programação II", "edu.someUniversity.cs1.Assignment4",
//            "git@github.com:someuser/cs1Assignment1.git", "Java",
//            0, "15/04/2022 00:10:00" ,"")
//        )
    }

    fun addTest(assignment: Assignment) {
        assignmentList.add(assignment)
        Log.i("AssignmentList", "Adicionado um novo assignment à lista")
    }
}
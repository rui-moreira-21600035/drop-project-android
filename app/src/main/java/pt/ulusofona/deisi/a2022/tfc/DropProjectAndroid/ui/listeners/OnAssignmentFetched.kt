package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners

import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment

interface OnAssignmentFetched {
    fun fillTextInputs(assignment: Assignment)
}
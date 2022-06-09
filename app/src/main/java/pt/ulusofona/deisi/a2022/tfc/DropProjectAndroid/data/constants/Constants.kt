package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.constants

import android.media.session.MediaSession

class Constants {
    companion object{
        /** Valores Constantes Gerais **/
        const val BASE_URL = ""
        const val REQUEST_CODE = 100
        const val TOKEN = "sihRf0g4ht0eNEa3MmdT" // TODO: ELIMINAR APÓS TESTES

        /** Valores Constantes dos Fragmentos **/
        const val SPLASHSCREEN_FRAGMENT                = 1
        const val LOGIN_FRAGMENT                       = 2
        const val ASSIGNMENTS_FRAGMENT                 = 3
        const val ASSIGNMENT_FORM_FRAGMENT             = 4
        const val SUBMISSIONS_FRAGMENT                 = 5
        const val SUBMISSION_DETAILS_FRAGMENT          = 6
        const val ARCHIVED_ASSIGNMENTS_FRAGMENT        = 7
        const val ARCHIVED_SUBMISSIONS_FRAGMENT        = 8
        const val ARCHIVED_SUBMISSION_DETAILS_FRAGMENT = 9
        const val ABOUT_FRAGMENT                       = 10
    }
}
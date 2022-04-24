package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.utils

import android.app.ActionBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.constants.Constants
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.fragments.AboutFragment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.fragments.AssignmentFormFragment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.fragments.AssignmentsFragment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.fragments.LoginFragment

class NavigationManager() {

    companion object {

        var currentFragment: Int? = null

        val loginFragment = LoginFragment()
        val assignmentsFragment = AssignmentsFragment()
        val assignmentFormFragment = AssignmentFormFragment()
        val aboutFragment = AboutFragment()

        private fun placeFragment(fm: FragmentManager, fragment: Fragment) {
            val transition = fm.beginTransaction()
            transition
                .setCustomAnimations(
                    R.anim.slide_in,
                    R.anim.fade_out,
                    R.anim.fade_in,
                    R.anim.slide_out
                )
                .replace(R.id.content_main, fragment)
                .addToBackStack(null)
                .commit()
        }

        fun goToLoginFragment(fm: FragmentManager, actionBar: androidx.appcompat.app.ActionBar) {
            placeFragment(fm, loginFragment)
            actionBar.hide()
            currentFragment = Constants.LOGIN_FRAGMENT
        }

        fun goToAssignmentsFragment(fm: FragmentManager, actionBar: androidx.appcompat.app.ActionBar) {
            placeFragment(fm, assignmentsFragment)
            actionBar.title = getFragmentName(Constants.ASSIGNMENTS_FRAGMENT)
            currentFragment = Constants.ASSIGNMENTS_FRAGMENT
        }

        fun goToAssignmentFormFragment(fm: FragmentManager, actionBar: androidx.appcompat.app.ActionBar) {
            placeFragment(fm, assignmentFormFragment)
            actionBar.title = getFragmentName(Constants.ASSIGNMENT_FORM_FRAGMENT)
            currentFragment = Constants.ASSIGNMENT_FORM_FRAGMENT
        }

        fun goToAboutFragment(fm: FragmentManager, actionBar: androidx.appcompat.app.ActionBar) {
            placeFragment(fm, aboutFragment)
            actionBar.title = getFragmentName(Constants.ABOUT_FRAGMENT)
            currentFragment = Constants.ABOUT_FRAGMENT
        }

        fun getFragmentName(fragNum: Int): String {
            /* TODO: Trocar Hardcode por Strings */
            var name = ""
            when (fragNum) {
                1 -> name = "SplashScreen"
                2 -> name = "Login"
                3 -> name = "Assignments"
                4 -> name = "Add/Edit Assignment"
                5 -> name = "Submissions"
                6 -> name = "Submissions Details"
                7 -> name = "Archived Assignments"
                8 -> name = "Archived Submissions"
                9 -> name = "Archived Submission Details"
                10 -> name = "About"
            }
            return name
        }
    }

}
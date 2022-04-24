package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.activities

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.navigation.NavigationView
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.constants.Constants
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.AssignmentsViewModel
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var viewModel: MainViewModel

    private lateinit var toolbar: MaterialToolbar

    private lateinit var navDrawer: NavigationView

    private lateinit var drawer: DrawerLayout

    private lateinit var alertDialog: AlertDialog.Builder

    private var isShowingToast = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()
        toolbar = findViewById(R.id.main_toolbar)
        drawer = findViewById(R.id.drawer)
        setSupportActionBar(toolbar)

        if (NavigationManager.currentFragment != null){
            goToCurrentFragment(supportFragmentManager)
        } else {
            supportActionBar?.hide()
            NavigationManager.goToLoginFragment(supportFragmentManager, supportActionBar!!)
        }

        navDrawer = findViewById(R.id.nav_drawer)
        setupDrawerMenu()
        alertDialog = AlertDialog.Builder(this)
            .setMessage("Do you really want to Sign Out?")
            .setPositiveButton("Sign Out") { dialog, which ->
                NavigationManager.goToLoginFragment(
                    supportFragmentManager,
                    supportActionBar!!
                )
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }
    }

    override fun onBackPressed() {
        /** Controla o comportamento do Menu Drawer e o sair da App ao clicar no botão Back**/
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START)
        }
        else if (supportFragmentManager.backStackEntryCount == 1){
            if (isShowingToast) finish()
            else showToast("Press back again to exit")
        }
        else super.onBackPressed()
    }

    private fun setupDrawerMenu(){
        val toggle = ActionBarDrawerToggle(this, drawer, toolbar, 0,0)
        navDrawer.setNavigationItemSelectedListener(this)
        drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.dm_nav_open_assignments -> NavigationManager.goToAssignmentsFragment(supportFragmentManager, supportActionBar!!)
            R.id.dm_nav_archived_assignments -> Toast.makeText(this, "Not implemented yet...", Toast.LENGTH_SHORT).show() //NavigationManager.goToArchivedAssignmentsFragment(supportFragmentManager, supportActionBar!!)
            R.id.dm_nav_about -> NavigationManager.goToAboutFragment(supportFragmentManager, supportActionBar!!)
            R.id.dm_nav_sign_out -> alertDialog.show()
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    fun showToast(msg: String){
        isShowingToast = true
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
        Handler(Looper.getMainLooper()).postDelayed(object : Runnable {
            override fun run() {
                isShowingToast = false
            }
        },2700)
    }

    fun goToCurrentFragment(fm: FragmentManager){
        when(NavigationManager.currentFragment){
            Constants.LOGIN_FRAGMENT -> {
                NavigationManager.goToLoginFragment(fm, supportActionBar!!)
                NavigationManager.currentFragment = Constants.LOGIN_FRAGMENT
            }
            Constants.ASSIGNMENTS_FRAGMENT -> {
                NavigationManager.goToAssignmentsFragment(fm, supportActionBar!!)
                NavigationManager.currentFragment = Constants.ASSIGNMENTS_FRAGMENT
            }
            Constants.ASSIGNMENT_FORM_FRAGMENT -> {
                NavigationManager.goToAssignmentFormFragment(fm, supportActionBar!!)
                NavigationManager.currentFragment = Constants.ASSIGNMENT_FORM_FRAGMENT
            }
            Constants.ABOUT_FRAGMENT -> {
                NavigationManager.goToAboutFragment(fm, supportActionBar!!)
                NavigationManager.currentFragment = Constants.ABOUT_FRAGMENT
            }

        }
    }
}
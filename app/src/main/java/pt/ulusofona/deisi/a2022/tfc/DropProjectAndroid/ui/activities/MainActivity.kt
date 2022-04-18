package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.widget.Toolbar
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
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
        supportActionBar?.hide()
        NavigationManager.goToLoginFragment(supportFragmentManager, supportActionBar!!)
        viewModel.currentFragment = Constants.LOGIN_FRAGMENT
        navDrawer = findViewById(R.id.nav_drawer)
        setupDrawerMenu()
    }

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START)
        else if (supportFragmentManager.backStackEntryCount == 1) finish()
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
            R.id.dm_nav_archived_assignments -> false//NavigationManager.goToArchivedAssignmentsFragment(supportFragmentManager, supportActionBar!!)
            R.id.dm_nav_about -> false//NavigationManager.goToAboutFragment(supportFragmentManager, supportActionBar!!)
            R.id.dm_nav_sign_out -> NavigationManager.goToLoginFragment(supportFragmentManager, supportActionBar!!)
        }
        drawer.closeDrawer(GravityCompat.START)
        return true
    }
}
package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.activities.MainActivity
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.LoginViewModel
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.MainViewModel

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var buttonLogin: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        buttonLogin = view?.findViewById(R.id.btn_login)!!

        buttonLogin.setOnClickListener {
            val ab = (activity as AppCompatActivity).supportActionBar!!
            requireActivity().supportFragmentManager.popBackStack()
            NavigationManager.goToAssignmentsFragment(parentFragmentManager, ab)                            // Chama o supportFragmentManager da MainActivity()
            ab.show()

        }
    }
}
package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnDatabaseEntry
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.AssignmentFormViewModel
import java.text.SimpleDateFormat
import java.util.*

class AssignmentFormFragment : Fragment(), OnDatabaseEntry{

    private lateinit var viewModel: AssignmentFormViewModel

    private var errorDrawable: Drawable? = null

    /** EditTexts **/
    private lateinit var inputId: TextInputEditText
    private lateinit var inputName: TextInputEditText
    private lateinit var inputTags: TextInputEditText
    private lateinit var inputPackage: TextInputEditText
    private lateinit var inputGit: TextInputEditText

    /** Dropdowns **/
    private lateinit var dropDownLang: AutoCompleteTextView
    private lateinit var dropDownLeaderboard: AutoCompleteTextView

    /** Botões **/
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    companion object {
        @JvmStatic fun newInstance(
            id: String, name: String, tags: String, packageName: String, gitRepo: String,
            programLang: String, leaderboardType: String) =
            AboutFragment().apply {
                arguments = Bundle().apply {
                    putString("id", id)
                    putString("name", name)
                    putString("tags", tags)
                    putString("packageName", packageName)
                    putString("gitRepo", gitRepo)
                    putString("programLang", programLang)
                    putString("leaderboardType", leaderboardType)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(AssignmentFormViewModel::class.java)
        arguments?.let {
            viewModel.assignId = it.getString("id")
            viewModel.assignName = it.getString("name")
            viewModel.assignTags = it.getString("tags")
            viewModel.assignPackage = it.getString("packageName")
            viewModel.assignGit = it.getString("gitRepo")
            viewModel.assignProgLang = it.getString("programLang")
            viewModel.assignLeaderType = it.getString("leaderboardType")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_assignment_form, container, false)
    }

    override fun onStart() {
        super.onStart()
        errorDrawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_input_error, null)
            errorDrawable?.setBounds(0, 0, errorDrawable?.getIntrinsicWidth()!!,
                    errorDrawable?.getIntrinsicHeight()!!)
        /** InputTexts **/
        inputId = requireActivity().findViewById(R.id.et_assign_id)
        inputName = requireActivity().findViewById(R.id.et_assign_name)
        inputTags = requireActivity().findViewById(R.id.et_assign_tags)
        inputPackage = requireActivity().findViewById(R.id.et_assign_package)
        inputGit = requireActivity().findViewById(R.id.et_assign_git)

        /** DropDowns **/
        val progLanguagesArray = requireActivity().resources.getStringArray(R.array.programming_languages)
        val arrayAdapterProgLangs = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, progLanguagesArray)
        dropDownLang = requireActivity().findViewById(R.id.autotv_prog_lang)
        dropDownLang.setAdapter(arrayAdapterProgLangs)

        val leaderboardTypesArray = requireActivity().resources.getStringArray(R.array.leaderboard_types)
        val arrayAdapterLeaderboardTypes = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, leaderboardTypesArray)
        dropDownLeaderboard = requireActivity().findViewById(R.id.autotv_leaderboard)
        dropDownLeaderboard.setAdapter(arrayAdapterLeaderboardTypes)

        /** Botões **/
        btnSave = requireActivity().findViewById(R.id.btn_save_form)
        btnCancel = requireActivity().findViewById(R.id.btn_cancel_form)

        loadInputs()

        /** OnClick Botão Save **/
        btnSave.setOnClickListener {
            if(!validateInputs()){
                val assignment = Assignment(
                    UUID.randomUUID(),inputId.text.toString(), inputName.text.toString(),
                    inputTags.text.toString(), "", inputPackage.text.toString(),
                    inputGit.text.toString(), dropDownLang.text.toString(),
                    dropDownLeaderboard.text.toString(),
                    SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date()), "")

                viewModel.insertEntryDB(assignment, this)
                requireActivity().supportFragmentManager.popBackStack()
                requireActivity().supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
            }
        }

        /** OnClick Botão Cancel **/
        btnCancel.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
            requireActivity().supportFragmentManager.popBackStack()
            clearInputs()
        }
    }

    fun loadInputs(){
        /** Carrega os argumentos para edição de assignments **/
        let {
            inputId.setText(viewModel.assignId)
            inputName.setText(viewModel.assignName)
            inputTags.setText(viewModel.assignTags)
            inputPackage.setText(viewModel.assignPackage)
            inputGit.setText(viewModel.assignGit)}
    }

    fun clearInputs(){
        inputId.setText("")
        inputName.setText("")
        inputTags.setText("")
        inputPackage.setText("")
        inputGit.setText("")
        dropDownLang.clearListSelection()
        dropDownLeaderboard.clearListSelection()
    }

    fun validateInputs(): Boolean{
        /** Valida os campos do formulário **/
        var error = false

        if (inputGit.text?.length == 0){
            inputGit.setError("This field cannot be blank", errorDrawable)
            inputGit.requestFocus()
            error = true
        }
        if (inputPackage.text?.length == 0){
            inputPackage.setError("This field cannot be blank", errorDrawable)
            inputPackage.requestFocus()
            error = true
        }
        if (inputName.text?.length == 0){
            inputName.setError("This field cannot be blank", errorDrawable)
            inputName.requestFocus()
            error = true
        }
        if (inputId.text?.length == 0){
            inputId.setError("This field cannot be blank", errorDrawable)
            inputId.requestFocus()
            error = true
        }

        return error
    }

    override fun onDBEntry() {
        requireActivity().supportFragmentManager.popBackStack()
        requireActivity().supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
    }

}
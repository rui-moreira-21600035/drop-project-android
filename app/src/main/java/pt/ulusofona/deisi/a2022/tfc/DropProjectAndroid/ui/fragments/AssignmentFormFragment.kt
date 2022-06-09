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
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentFetched
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnDataChanged
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnDatabaseEntry
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.AssignmentFormViewModel
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class AssignmentFormFragment : Fragment(), OnDatabaseEntry, OnAssignmentFetched{
    private var id: String? = null

    private lateinit var viewModel: AssignmentFormViewModel

    private var errorDrawable: Drawable? = null

    private var isNewAssignment = true

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
        @JvmStatic fun newInstance(id: String) = AssignmentFormFragment().apply {
                arguments = Bundle().apply {
                    putString("id", id)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getString("id")!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AssignmentFormViewModel::class.java)
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

        if(!id.isNullOrEmpty()){
            viewModel.getAssignmentById(id.toString(), this)
            isNewAssignment = false
        }

        /** OnClick Botão Save **/
        btnSave.setOnClickListener {
            if(!validateInputs()){
                val assignment = Assignment(
                    inputId.text.toString(), inputName.text.toString(),
                    inputTags.text.toString(), "", inputPackage.text.toString(),
                    inputGit.text.toString(), dropDownLang.text.toString(),
                    dropDownLeaderboard.text.toString(),
                    SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date()), "")
                clearInputs()
                if(isNewAssignment){
                    viewModel.insertEntryDB(assignment, this)
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireActivity().applicationContext,
                            "Assignment \"${assignment.title}\" created successfully",
                            Toast.LENGTH_SHORT).show() }
                } else {
                    viewModel.updateEntryDB(assignment, this)
                    requireActivity().runOnUiThread {
                        Toast.makeText(
                            requireActivity().applicationContext,
                            "Assignment \"${assignment.title}\" edited successfully",
                            Toast.LENGTH_SHORT).show() }
                }

            }
        }

        /** OnClick Botão Cancel **/
        btnCancel.setOnClickListener {
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
            requireActivity().supportFragmentManager.popBackStack()
            clearInputs()
        }
    }

    fun loadInputs(assignment: Assignment){
        /** Carrega os argumentos para edição de assignments **/
            inputId.setText(assignment.id)
            inputName.setText(assignment.title)
            inputTags.setText(assignment.tags)
            inputPackage.setText(assignment.packageName)
            inputGit.setText(assignment.gitRepoURL)
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

    override fun fillTextInputs(assignment: Assignment) {
        activity?.runOnUiThread {
            loadInputs(assignment)
        }
    }

}
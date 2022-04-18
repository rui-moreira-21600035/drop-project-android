package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R

class AssignmentFormFragment : Fragment() {

    private lateinit var dropDownLang: AutoCompleteTextView
    private lateinit var btnSave: Button
    private lateinit var btnCancel: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_assignment_form, container, false)
    }

    override fun onStart() {
        super.onStart()
        val progLanguagesArray = requireActivity().resources.getStringArray(R.array.programming_languages)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.support_simple_spinner_dropdown_item, progLanguagesArray)

        dropDownLang = requireActivity().findViewById(R.id.autotv_prog_lang)
        dropDownLang.setAdapter(arrayAdapter)
        btnSave = requireActivity().findViewById(R.id.btn_save_form)
        btnCancel = requireActivity().findViewById(R.id.btn_cancel_form)

        /** Botão Save **/
        btnSave.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
        }

        /** Botão Cancel **/
        btnCancel.setOnClickListener {
            requireActivity().supportFragmentManager.popBackStack()
            requireActivity().supportFragmentManager.beginTransaction().remove(this).commitAllowingStateLoss()
        }
    }
}
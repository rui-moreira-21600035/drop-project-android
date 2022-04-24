package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.adapters.AssignmentAdapter
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.listeners.OnAssignmentsLoaded
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.AssignmentsViewModel


class AssignmentsFragment : Fragment(), OnAssignmentsLoaded {

    private lateinit var viewModel: AssignmentsViewModel

    lateinit var assignmentsRecyclerView: RecyclerView

    lateinit var fab: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this).get(AssignmentsViewModel::class.java)
        return inflater.inflate(R.layout.assignments_fragment, container, false)
    }

    override fun onStart() {
        super.onStart()
        assignmentsRecyclerView = requireActivity().findViewById(R.id.assignments_recycler_view)
        fab = requireActivity().findViewById(R.id.fab_new_assignment)
        registerForContextMenu(assignmentsRecyclerView)
        viewModel.registerListener(this)
        assignmentsRecyclerView.adapter = AssignmentAdapter(ArrayList<Assignment>(),viewModel, activity as AppCompatActivity)
        viewModel.loadAssignments()

        fab.setOnClickListener {
            val ab = (activity as AppCompatActivity).supportActionBar!!
            NavigationManager.goToAssignmentFormFragment(parentFragmentManager, ab)                            // Chama o supportFragmentManager da MainActivity()
            ab.show()
        }
    }

    fun refreshAdapter(assignmentList: ArrayList<Assignment>){
        assignmentsRecyclerView.adapter = AssignmentAdapter(assignmentList, viewModel, activity as AppCompatActivity)
    }

    override fun onDestroy() {
        viewModel.unregisterListener()
        super.onDestroy()
    }

    override fun onAssignmentsLoaded(assignmentList: ArrayList<Assignment>) {
        /** Pede à UiThread para alterar a RecyclerView após notificado pelo listener do VM **/
        activity?.runOnUiThread {
            refreshAdapter(assignmentList)
        }
    }



}
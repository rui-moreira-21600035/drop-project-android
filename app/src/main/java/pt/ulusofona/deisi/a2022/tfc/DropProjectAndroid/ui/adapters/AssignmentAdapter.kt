package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.adapters

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.utils.NavigationManager
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.AssignmentsViewModel
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.viewmodels.MainViewModel

class AssignmentAdapter(private val dataSet: ArrayList<Assignment>, private val viewModel: AssignmentsViewModel, private val activity: FragmentActivity):
    RecyclerView.Adapter<AssignmentAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val resultImageView: ImageView
        val titleTextView: TextView
        val subjectTextView: TextView
        val dateCreatedTextView: TextView
        val context: Context

        init {
            resultImageView = view.findViewById(R.id.iv_ic_assignment)
            titleTextView = view.findViewById(R.id.tv_assignment_title)
            subjectTextView = view.findViewById(R.id.tv_assignment_subject)
            dateCreatedTextView = view.findViewById(R.id.tv_date_created)
            context = view.context
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_assignment, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val context: Context = viewHolder.context

        val assignment = getItem(position)

        val secondAlertDialog = AlertDialog.Builder(context)
            .setMessage("This action will be IRREVERSIBLE, are you sure you want to proceed?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Yes, I'm Sure") { dialog, which ->
                viewModel.deleteAssignmentById(assignment.id)
                Toast.makeText(context, "${assignment.title} deleted", Toast.LENGTH_SHORT)
                    .show()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        val firstAlertDialog = AlertDialog.Builder(context)
            .setMessage("Do you really want to delete this assignment?")
            .setIcon(R.drawable.ic_input_error)
            .setPositiveButton("Delete") { dialog, which ->
                secondAlertDialog.show()
            }
            .setNegativeButton("Cancel") { dialog, which ->
                dialog.cancel()
            }

        /** Define as infos para cada Card **/
        viewHolder.titleTextView.text = assignment.title
        viewHolder.subjectTextView.text = assignment.subject
        viewHolder.dateCreatedTextView.text = assignment.dateCreated

        /** Ao pressionar longo, apresenta Menu de contexto para editar ou eliminar assignment **/
        viewHolder.itemView.setOnLongClickListener {
            val items = arrayOf("Edit", "Delete")

            MaterialAlertDialogBuilder(context)
            .setTitle(assignment.title)
            .setItems(items) { dialog, index ->
                when(index) {
                    0 -> {
                        // ** TODO: Abre o AssignmentFormFragment para editar Assignment
                        val ab = (activity as AppCompatActivity).supportActionBar!!
                        NavigationManager.goToAssignmentFormFragment(activity.supportFragmentManager, ab)
                        ab.show()
                    }
                    // TODO: AlertDialog para confirmar eliminar Assignment
                    1 -> {
                        firstAlertDialog.show()
//                        viewModel.deleteAssignmentById(assignment.id)
//                        Toast.makeText(context, "${assignment.title} deleted", Toast.LENGTH_SHORT)
//                            .show()
                    }
                }
            }.show()
            true
        }

        /** Ao clicar, abrir SubmissionsFragment com submissões do respectivo Assignment **/
        viewHolder.itemView.setOnClickListener {
            //TODO: Alterar acção ao clicar no card -> Ecrã Submissões
        }

    }

    private fun getItem(position: Int): Assignment {
        return dataSet[position]
    }

    override fun getItemCount() = dataSet.size

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}
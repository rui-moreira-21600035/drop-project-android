package pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.ui.adapters

import android.content.Context
import android.view.*
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.R
import pt.ulusofona.deisi.a2022.tfc.DropProjectAndroid.data.local.room.entities.Assignment

class AssignmentAdapter(private val dataSet: ArrayList<Assignment>):
    RecyclerView.Adapter<AssignmentAdapter.ViewHolder>(){

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val resultImageView: ImageView
        val titleTextView: TextView
        val subjectTextView: TextView
        val dateCreatedTextView: TextView
        val context: Context

        init {
            // Define click listener for the ViewHolder's View.
            resultImageView = view.findViewById(R.id.iv_ic_assignment)
            titleTextView = view.findViewById(R.id.tv_assignment_title)
            subjectTextView = view.findViewById(R.id.tv_assignment_subject)
            dateCreatedTextView = view.findViewById(R.id.tv_date_created)
            context = view.context
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_assignment, viewGroup, false)
        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        val context: Context = viewHolder.context

        val assignment = getItem(position)!!

//        var ID = assignment.uuid
//        var DATE = assignment.date
//        var LOCAL = assignment.local
//        var RESULT = assignment.resultado
//        var FILE = assignment.ficheiro

        /** Define as infos para cada Card **/
        viewHolder.titleTextView.text = assignment.title
        viewHolder.subjectTextView.text = assignment.subject
        viewHolder.dateCreatedTextView.text = assignment.dateCreated

        viewHolder.itemView.setOnLongClickListener {
            val items = arrayOf("Edit", "Delete")

            val alertDiag = MaterialAlertDialogBuilder(context)
            .setTitle(assignment.title)
            .setItems(items) { dialog, index ->
                val toast = Toast.makeText(context,"Item #${index} selected", Toast.LENGTH_SHORT)
            }.show()
            true
        }

//       TODO: Alterar acção ao clicar no card
//        viewHolder.itemView.setOnClickListener {
//            val intent = Intent(context, SubmissionsFragment::class.java)
//            intent.apply {
//                putExtra("ID", ID)
//                putExtra("DATE", DATE)
//                putExtra("LOCAL", LOCAL)
//                putExtra("RESULT", RESULT)
//                putExtra("FILE", FILE)
//            }
//            context.startActivity(intent)
//        }

    }

    private fun getItem(position: Int): Assignment {
        return dataSet[position]
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

    override fun getItemId(position: Int): Long {
        return super.getItemId(position)
    }
}
package com.pytel.notes.ui.notes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pytel.notes.R
import com.pytel.core.domain.model.Note
import kotlinx.android.synthetic.main.view_note_item.view.*


class NotesAdapter(private val onNoteCallback: (Note) -> Unit) : RecyclerView.Adapter<NotesAdapter.NotesViewHolder>() {

    private var items = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_note_item, parent, false)
        return NotesViewHolder(itemView = view)
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bindNote(items[position],onNoteCallback)
    }

    override fun getItemCount() = items.size

    fun setNotes(notes: List<Note>) {
        this.items = notes.toMutableList()
        notifyDataSetChanged()
    }

    class NotesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindNote(note: Note, onNoteCallback: (Note) -> Unit) {
            with(itemView) {
                title.text = note.title
                setOnClickListener { onNoteCallback(note) }
            }
        }
    }

}
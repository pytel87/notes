package com.pytel.notes.framework.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pytel.notes.R
import com.pytel.notes.domain.common.ErrorResult
import com.pytel.notes.domain.model.Note
import com.pytel.notes.framework.utils.isLandscape
import com.pytel.notes.framework.utils.logDebug
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.viewModel


class NotesFragment : Fragment() {

    private val viewModel by viewModel<NotesViewModel>()

    private lateinit var notesAdapter: NotesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        registerEvents()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesAdapter = NotesAdapter( { onNoteSelectedEvent(it) })
        recycler.apply {
            layoutManager = activity?.let { GridLayoutManager(it, getSpanCount()) }
            adapter = notesAdapter
        }

    }

    private fun onNoteSelectedEvent(note: Note) {
        logDebug ("Selected note with id: ${note.id}")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        recycler.adapter = null
    }

    private fun registerEvents() {
        viewModel.viewState.observe(this, Observer {
            showLoading(it.showLoading)
            showError(null)
            logDebug("Notes state observed: ${it.javaClass.simpleName}")
            when (it) {
                is NotesStates.NotInitialized -> viewModel.loadNotes()
                is NotesStates.Loading -> logDebug ("Loading notes ...")
                is NotesStates.NotesLoaded-> showLoadedNotes(it.notes)
                is NotesStates.LoadingError -> showError(it.error)
            }
        })
    }

    private fun showLoading(show: Boolean) {
        progress.visibility =
            if (show) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    private fun showLoadedNotes(notes: List<Note>) {
        logDebug("Notes loaded. Size = ${notes.size}")
        notesAdapter.setNotes(notes)
    }

    private fun showError(error: ErrorResult?) {
        if (error == null) {
            return
        }

        val message = "Something went wrong: ${error.message}"
        logDebug(message)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()

    }


    override fun onStart() {
        super.onStart()
        viewModel.checkIfValidData()
    }

    private fun getSpanCount(): Int {
        val context = activity
        return if (context != null && context.isLandscape()) {
            3
        } else {
            2
        }
    }


}

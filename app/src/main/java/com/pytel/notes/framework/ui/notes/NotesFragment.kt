package com.pytel.notes.framework.ui.notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.pytel.notes.R
import com.pytel.notes.domain.common.ErrorResult
import com.pytel.notes.domain.model.Note
import com.pytel.notes.framework.base.BaseFragment
import com.pytel.notes.framework.ui.note.NoteStates
import com.pytel.notes.framework.utils.Const
import com.pytel.notes.framework.utils.isLandscape
import com.pytel.notes.framework.utils.logDebug
import kotlinx.android.synthetic.main.fragment_notes.*
import org.koin.androidx.viewmodel.ext.viewModel


class NotesFragment : BaseFragment() {

    companion object {
        const val TAG = "NotesFragment"
    }

    private val viewModel by viewModel<NotesViewModel>()

    private lateinit var notesAdapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(false)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_notes, container, false)
        registerEvents()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        notesAdapter = NotesAdapter( { onNoteSelectedEvent(it) })
        recycler.apply {
            layoutManager = activity?.let { StaggeredGridLayoutManager(getSpanCount(), LinearLayoutManager.VERTICAL) }
            adapter = notesAdapter
        }

        noteContainer.setOnClickListener{
            val extras = FragmentNavigatorExtras(
                note to "title"
            )
            findNavController().navigate(R.id.action_notesFragment_to_noteFragment, null, null, extras)
            viewModel.invalidate()
            logEvent("NoteCreate")
        }

        swipeRefresh.setOnRefreshListener {
            viewModel.loadNotes()
            swipeRefresh.isRefreshing = false
            logEvent("NotesRefresh")
        }

    }

    private fun onNoteSelectedEvent(note: Note) {
        logDebug ("Selected note with id: ${note.id}")
        val bundle = bundleOf(Const.SELECTED_NOTE_ID to note.id)
        findNavController().navigate(R.id.action_notesFragment_to_noteFragment, bundle)
        viewModel.invalidate()

        logEvent("NoteSelected")
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
                is NotesStates.NotInitialized -> {
                    logScreen(TAG)
                    viewModel.loadNotes()
                }
                is NotesStates.Loading -> logDebug ("Loading notes ...")
                is NotesStates.NotesLoaded-> showLoadedNotes(it.notes)
                is NotesStates.NotesError -> showError(it.error)
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
        logError(TAG, error.message)

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

package com.pytel.notes.framework.ui.note

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.pytel.notes.domain.common.ErrorResult
import com.pytel.notes.domain.model.Note
import com.pytel.notes.framework.utils.*
import kotlinx.android.synthetic.main.fragment_note.*
import kotlinx.android.synthetic.main.fragment_notes.progress
import org.koin.androidx.viewmodel.ext.viewModel
import android.view.inputmethod.EditorInfo
import com.pytel.notes.R
import com.pytel.notes.framework.base.BaseFragment


class NoteFragment : BaseFragment() {

    companion object {
        const val TAG = "NotesFragment"
    }

    private val viewModel by viewModel<NoteViewModel>()

    private var noteId : Int = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_note, container, false)
        registerEvents()
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        noteId = arguments?.getInt(Const.SELECTED_NOTE_ID, 0) ?: 0
        logInfo("Note detail for note with id: $noteId")



        title.setOnEditorActionListener { _, actionId, _->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                hideKeyboard()
                findNavController().navigateUp()
                logEvent("NoteDone")
                true
            } else {
                false
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        sharedElementEnterTransition = TransitionInflater.from(context).inflateTransition(android.R.transition.move)
    }

    private fun mapNoteToUI(note:Note) {
        title.setText(note.title)
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.menu_main, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            R.id.action_delete -> {
                viewModel.deleteNote(noteId)
                logEvent("NoteDelete")
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    }


    private fun registerEvents() {
        viewModel.viewState.observe(this, Observer {
            showLoading(it.showLoading)
            showError(null)
            logDebug("Notes state observed: ${it.javaClass.simpleName}")
            when (it) {
                is NoteStates.NotInitialized -> {
                    logScreen(TAG)
                    loadOrCreateEmptyNote()
                }
                is NoteStates.Loading -> logDebug ("Loading note ...")
                is NoteStates.NoteCreated-> {
                    logEvent("NoteCreated")
                    onNoteCreated(it.note)
                }
                is NoteStates.NoteLoaded-> onNoteLoaded(it.note)
                is NoteStates.NoteError -> showError(it.error)
                is NoteStates.NoteDeleted -> {
                    logEvent("NoteDeleted")
                    findNavController().navigateUp()
                }
            }
        })
    }

    private fun loadOrCreateEmptyNote() {
        if(noteId == 0){
            viewModel.createNote(title.text.toString())
        }else{
            viewModel.loadNote(noteId)
        }
    }

    private fun onNoteCreated(note:Note){
        mapNoteToUI(note)
    }

    private fun onNoteLoaded(note:Note){
        mapNoteToUI(note)
        title.setSelection(title.text.length)
        showSoftKeyboard(title)
    }

    private fun showLoading(show: Boolean) {
        progress.visibility =
            if (show) {
                View.VISIBLE
            } else {
                View.GONE
            }
    }

    private fun showError(error: ErrorResult?) {
        if (error == null) {
            return
        }

        val message = "Something went wrong: ${error.message}"
        logDebug(message)
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
        logError(TAG,error.message)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        updateOnDestroy()
    }

    override fun onDestroy() {
        super.onDestroy()
        hideKeyboard()
    }

    private fun updateOnDestroy() {

        if (noteId != 0) {
            viewModel.updateNote(noteId, title.text.toString())
        }
    }


    override fun onStart() {
        super.onStart()
        viewModel.checkIfValidData()
    }


}

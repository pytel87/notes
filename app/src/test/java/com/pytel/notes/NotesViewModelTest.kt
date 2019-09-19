package com.pytel.notes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.whenever
import com.pytel.notes.domain.common.ErrorResult
import com.pytel.notes.domain.common.Result
import com.pytel.notes.domain.model.Note
import com.pytel.notes.domain.usecase.NotesUseCase
import com.pytel.notes.framework.base.TestContextProvider
import com.pytel.notes.framework.ui.notes.NotesStates
import com.pytel.notes.framework.ui.notes.NotesViewModel
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito
import java.lang.RuntimeException


@RunWith(JUnit4::class)
class NotesViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var notesUseCase: NotesUseCase

    @Mock
    lateinit var viewStateObserver: Observer<NotesStates>

    lateinit var notesViewModel: NotesViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        notesViewModel = NotesViewModel(notesUseCase, TestContextProvider()).apply {
            viewState.observeForever(viewStateObserver)
        }
    }

    @Test
    fun loadNotes_success() = testCoroutineRule.runBlockingTest {

        val data = listOf(Note(1, "This is important note"))

        whenever(notesUseCase.getAllNotes()).thenReturn(Result.Success(data))

        notesViewModel.loadNotes()

        val orderVerifier = Mockito.inOrder(viewStateObserver)

        orderVerifier.verify(viewStateObserver).onChanged(NotesStates.NotInitialized)
        orderVerifier.verify(viewStateObserver).onChanged(NotesStates.Loading)
        orderVerifier.verify(viewStateObserver).onChanged(NotesStates.NotesLoaded(data))

    }

    @Test
    fun loadNotes_error() = testCoroutineRule.runBlockingTest {

        val exception = RuntimeException("Crash")
        val error = ErrorResult(throwable = exception)

        whenever(notesUseCase.getAllNotes()).thenReturn(Result.Error(error))

        notesViewModel.loadNotes()

        val orderVerifier = Mockito.inOrder(viewStateObserver)

        orderVerifier.verify(viewStateObserver).onChanged(NotesStates.NotInitialized)
        orderVerifier.verify(viewStateObserver).onChanged(NotesStates.Loading)
        orderVerifier.verify(viewStateObserver).onChanged(NotesStates.NotesError(error))

    }

}
package com.example.mon7.presentation.fragments.create_edit_note

import android.os.Bundle
import android.provider.Contacts.SettingsColumns.KEY
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.mon7.R
import com.example.mon7.databinding.FragmentCreateNoteBinding
import com.example.mon7.domain.model.Note
import com.example.mon7.presentation.base.BaseFragment
import com.example.mon7.presentation.utils.UIState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreateEditNoteFragment : BaseFragment(R.layout.fragment_create_note) {

    private val binding by viewBinding(FragmentCreateNoteBinding::bind)
    private val viewModel by viewModels<CreateEditNoteViewModel>()
    private var note: Note? = null
    override fun initClickListeners() {
        note = arguments?.getSerializable(KEY) as Note?
        binding.etTitle.setText(note?.title)
        binding.etDesc.setText(note?.description)
        if (note == null) {
            binding.btnSave.setOnClickListener {
                viewModel.createNote(
                    Note(
                        title = binding.etTitle.text.toString(),
                        description = binding.etDesc.text.toString()
                    )
                )
            }
        } else {

            binding.btnSave.text = "Update"
            binding.btnSave.setOnClickListener {
                note!!.title = binding.etTitle.text.toString()
                note!!.description = binding.etDesc.text.toString()
                viewModel.updateNote(
                    note!!
                )
            }

        }
    }

    override fun setupObservers() {
        viewModel.createNoteState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                findNavController().navigateUp()
            }
        )

        viewModel.updateNoteState.collectUIState(
            state = {
                binding.progressBar.isVisible = it is UIState.Loading
            },
            onSuccess = {
                findNavController().navigateUp()
            }
        )
    }
}
package com.example.mon7.presentation.fragments.list_note.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mon7.databinding.ItemNoteBinding
import com.example.mon7.domain.model.Note

class ListNoteAdapter(
    private val deleteNote: (Note) -> Unit,
    private val updateNote: (Note) -> Unit
) : RecyclerView.Adapter<ListNoteAdapter.ListNoteViewHolder>() {

    private var list = mutableListOf<Note>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListNoteViewHolder {
        return ListNoteViewHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ListNoteViewHolder, position: Int) {
        holder.bind(list[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addList(list: List<Note>) {
        this.list = list as ArrayList<Note>
        notifyDataSetChanged()
    }
    @SuppressLint("NotifyDataSetChanged")
    fun delete(liste: Note) {
        list.remove(liste)
        notifyDataSetChanged()
    }

    inner class ListNoteViewHolder(private val binding: ItemNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(note: Note) {
            binding.tvTitle.text = note.title
            binding.tvDesc.text = note.description

            itemView.setOnClickListener {
                updateNote(note)
            }

            itemView.setOnLongClickListener {
                deleteNote(note)
                false
            }
        }
    }
}
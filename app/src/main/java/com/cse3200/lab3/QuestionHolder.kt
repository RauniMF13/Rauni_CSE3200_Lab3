package com.cse3200.lab3

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cse3200.lab3.databinding.QuestionItemBinding

class QuestionHolder (
    val binding: QuestionItemBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(question : Question) {
        binding.questionText.setText(question.textResId)
        binding.thumbnailImage.setImageResource(question.imageResId)
    }
}

class QuestionListAdapter(
    private val questions : List<Question>
) : RecyclerView.Adapter<QuestionHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): QuestionHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestionItemBinding.inflate(inflater, parent, false)
        return QuestionHolder(binding)
    }

    override fun onBindViewHolder(
        holder: QuestionHolder,
        position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount() = questions.size
}
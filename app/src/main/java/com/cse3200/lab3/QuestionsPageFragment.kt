package com.cse3200.lab3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.cse3200.lab3.databinding.FragmentQuestionsPageBinding

class QuestionsPageFragment : Fragment() {

    private var _binding: FragmentQuestionsPageBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuestionsPageBinding.inflate(layoutInflater, container, false)

        binding.questionsRecyclerView.layoutManager = LinearLayoutManager(context)

        val adapter = QuestionListAdapter(questionBank)

        binding.questionsRecyclerView.adapter = adapter

        return binding.root
    }

    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true, firstGuess = true, R.drawable.canberra_stock),
        Question(R.string.question_oceans, answer = true, firstGuess = true, R.drawable.pacific_ocean),
        Question(R.string.question_mideast, answer = false, firstGuess = true, R.drawable.suez_canal),
        Question(R.string.question_africa, answer = false, firstGuess = true, R.drawable.nile_river),
        Question(R.string.question_americas, answer = true, firstGuess = true, R.drawable.amazon_river),
        Question(R.string.question_asia, answer = true, firstGuess = true, R.drawable.baikal_stock)
    )

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
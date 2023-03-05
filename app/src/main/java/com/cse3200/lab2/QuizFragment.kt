package com.cse3200.lab2

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cse3200.lab2.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private var _binding: FragmentQuizBinding? = null

    private lateinit var bundle : Bundle

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQuizBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.trueButton.setOnClickListener {
            checkAnswer(true)
            updateQuestion()
        }

        binding.falseButton.setOnClickListener {
            checkAnswer(false)
            updateQuestion()
        }

        binding.nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        binding.prevButton.setOnClickListener {
            if (currentIndex > 0) {
                currentIndex = (currentIndex - 1)
                updateQuestion()
            }
        }

        binding.doneButton.setOnClickListener {
            // Navigate to done fragment
            val action = QuizFragmentDirections.submit()

            findNavController().navigate(action)
        }

        updateQuestion()
    }

    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true, firstGuess = true),
        Question(R.string.question_oceans, answer = true, firstGuess = true),
        Question(R.string.question_mideast, answer = false, firstGuess = true),
        Question(R.string.question_africa, answer = false, firstGuess = true),
        Question(R.string.question_americas, answer = true, firstGuess = true),
        Question(R.string.question_asia, answer = true, firstGuess = true)
    )

    private val scoreTexts = listOf(
        R.string.score_zero,
        R.string.score_one,
        R.string.score_two,
        R.string.score_three,
        R.string.score_four,
        R.string.score_five,
        R.string.score_six
    )

    private var currentIndex = 0

    private var score = Score(0)

    private fun updateQuestion() {
        val questionTextResId = questionBank[currentIndex].textResId
        binding.textView2.setText(questionTextResId)
        binding.scoreText.setText(scoreTexts[score.curScore])
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        if (messageResId == R.string.incorrect_toast) {
            questionBank[currentIndex].firstGuess = false
        }

        if (messageResId == R.string.correct_toast) {
            if (questionBank[currentIndex].firstGuess) {
                score.curScore = score.curScore + 1
            }
            questionBank[currentIndex].firstGuess = false
            currentIndex = (currentIndex + 1) % questionBank.size
        }

        Toast.makeText(
            activity,
            messageResId,
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
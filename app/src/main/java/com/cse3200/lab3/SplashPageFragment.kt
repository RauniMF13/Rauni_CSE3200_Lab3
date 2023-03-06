package com.cse3200.lab3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.cse3200.lab3.databinding.FragmentSplashPageBinding

class SplashPageFragment : Fragment() {

    private var _binding: FragmentSplashPageBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashPageBinding.inflate(layoutInflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.launchButton.setOnClickListener {
            val action = SplashPageFragmentDirections.start()
            findNavController().navigate(action)
        }

        binding.questionsPageButton.setOnClickListener {
            val action = SplashPageFragmentDirections.toQuestions()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
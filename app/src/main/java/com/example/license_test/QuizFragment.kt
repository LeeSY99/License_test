package com.example.license_test

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.license_test.databinding.FragmentQuizBinding



/**
 * A simple [Fragment] subclass.
 * Use the [QuizFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class QuizFragment : Fragment() {
    private lateinit var binding: FragmentQuizBinding
    private val viewModel: QuizViewModel by viewModels()
    private var cutOffScore: Int=0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            cutOffScore = it.getInt("cutOffScore", 0)
        }

        // 커트라인 점수 확인 (예시로 로그에 출력)
        Log.d("QuizFragment", "Cut off score: $cutOffScore")

        // Inflate the layout for this fragment
        binding = FragmentQuizBinding.inflate(inflater, container,false)
        binding.viewModel=viewModel
        binding.lifecycleOwner=this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submitButton.setOnClickListener {onSubmit()}
    }

    private fun getSelectedIndex(): Int{
        val selectedRadioButtonId = binding.answerRadioGroup.checkedRadioButtonId
        val selectedRadioButton: RadioButton? = when (selectedRadioButtonId) {
            binding.option1RadioButton.id -> binding.option1RadioButton
            binding.option2RadioButton.id -> binding.option2RadioButton
            binding.option3RadioButton.id -> binding.option3RadioButton
            binding.option4RadioButton.id -> binding.option4RadioButton
            else -> null
        }
        return selectedRadioButton?.let { binding.answerRadioGroup.indexOfChild(it) } ?: -1
    }
    private fun onSubmit(){

        val selectedIndex = (getSelectedIndex()+1).toString()
        val isCorrect = viewModel.isPlayerCorrect(selectedIndex)
        Log.d("QuizFragment", "selected index $selectedIndex")
        if(isCorrect){
            viewModel.increaseScore()
        }
        viewModel.increaseCount()
        val currentcount=viewModel.currentQuizCount.value
        val currentscore=viewModel.score.value
        if (currentcount ==20){
            binding.submitButton.text = "제출하기"
        } else if (currentcount != null) {
            if(currentcount > 20){
                val bundle = Bundle().apply {
                    if (currentscore != null) {
                        putInt("currentscore", currentscore)
                    }
                }
                val navController = findNavController()
                if(viewModel.score.value!! >= cutOffScore){
                    navController.navigate(R.id.quiztopass, bundle)
                }
                else{
                    navController.navigate(R.id.quiztofail, bundle)
                }
            }
        }

        viewModel.getNextProblem()
        binding.answerRadioGroup.clearCheck()
    }

}
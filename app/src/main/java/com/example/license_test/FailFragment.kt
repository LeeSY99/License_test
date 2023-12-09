package com.example.license_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.license_test.databinding.FragmentFailBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FailFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentFailBinding
    private var userScore:Int =0


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        arguments?.let {
            userScore = it.getInt("currentscore", 0)
        }
        // Inflate the layout for this fragment
        binding = FragmentFailBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.failScore.text="획득점수: ${userScore}점"
        val navController = findNavController()
        binding.goHomeFail.setOnClickListener{
            navController.navigate(R.id.failtohome)
        }
        binding.retryButton.setOnClickListener{
            navController.navigate(R.id.failtoquiz)
        }
    }
}
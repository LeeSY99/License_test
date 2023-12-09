package com.example.license_test

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.license_test.databinding.FragmentHomeBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.homeStartButton.setOnClickListener{
            val selectedRadioButtonId = binding.licenseOption.checkedRadioButtonId

            // 면허 종류에 따라 커트라인 점수 설정
            val cutOffScore = when (selectedRadioButtonId) {
                R.id.opt1 -> 70 // 1종대형 선택 시 커트라인 점수 80 설정
                R.id.opt2 -> 70 // 1종보통 선택 시 커트라인 점수 75 설정
                R.id.opt3 -> 60 // 2종보통 선택 시 커트라인 점수 70 설정
                else -> 0
            }
            val bundle = Bundle().apply {
                putInt("cutOffScore", cutOffScore)
            }
            val navController = findNavController()
            navController.navigate(R.id.hometoquiz, bundle)
        }

    }
}
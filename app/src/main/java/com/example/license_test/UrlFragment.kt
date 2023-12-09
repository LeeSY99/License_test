package com.example.license_test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.license_test.databinding.FragmentUrlBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UrlFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UrlFragment : Fragment() {
    // TODO: Rename and change types of parameters
    lateinit var binding: FragmentUrlBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUrlBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // ViewBinding을 사용하여 버튼 클릭 이벤트 처리
        binding.buttonOpenWebPage.setOnClickListener {
            val url = "https://www.safedriving.or.kr/mainM.do"  // 여기에 열고자 하는 웹 페이지의 URL을 입력하세요.
            openWebPage(url)
        }
    }

    private fun openWebPage(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            // 웹 브라우저가 설치되어 있지 않은 경우에 대한 처리
            // 여기에 적절한 로직을 추가하세요.
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        var _binding = null
    }
}
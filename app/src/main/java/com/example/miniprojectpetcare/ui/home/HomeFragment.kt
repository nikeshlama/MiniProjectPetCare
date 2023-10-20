package com.example.miniprojectpetcare.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.miniprojectpetcare.R
import com.example.miniprojectpetcare.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // You can use the ImageViews in your layout to display static images
        // For example, if you have three ImageViews with IDs "imageView1", "imageView2", and "imageView3" in fragment_home.xml:
        // You can set the static images like this:

        binding.imageView1.setImageResource(R.drawable.doctor1)


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

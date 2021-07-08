package com.lucasesteves.beerloversapp

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager.widget.ViewPager
import com.lucasesteves.beerloversapp.R
import com.lucasesteves.beerloversapp.databinding.FragmentHomeBinding



class homeFragment : Fragment() {
  private var binding: FragmentHomeBinding? = null




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)










    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root


//        binding?.deslogar?.setOnClickListener{
//
//            startActivity(Intent(activity, LoginActivity::class.java))
//
//        }

    }





    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package com.lucasesteves.beerloversapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.lucasesteves.beerloversapp.databinding.FragmentLoginBinding


class ConfigContaFragment : Fragment() {
    private var binding: FragmentLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding?.root

    }
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

}
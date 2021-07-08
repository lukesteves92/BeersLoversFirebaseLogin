package com.lucasesteves.beerloversapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.lucasesteves.beerloversapp.databinding.FragmentAgendamentosBinding

class agendamentosFragment : Fragment() {
    private var binding: FragmentAgendamentosBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAgendamentosBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentsList = listOf(ContaFragment(), ConfigContaFragment())
        val fragmentsTitleList = listOf("Conta", "Configuração")

        activity?.let {
            val viewPagerAdapter = ViewPagerAdapter(
                fragmentManager = it.supportFragmentManager,
                fragmentsList = fragmentsList,
                fragmentsTitleList = fragmentsTitleList
            )

            binding?.let {  bindingNonNull ->
                with(bindingNonNull) {
                    vpContaTabs.adapter = viewPagerAdapter
                    ContaTabs.setupWithViewPager(vpContaTabs)
                }
            }
        }

//        binding?.tlPaymentTabs?.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                Log.i("tab - selected", tab?.text.toString())
//                when(tab?.position) {
//                    0 -> {
//                        goToFragment(Tab1Fragment())
//                    }
//                    1 -> {
//                        goToFragment(Tab2Fragment())
//                    }
//                    2 -> {
//                        goToFragment(Tab3Fragment())
//                    }
//                }
//            }
//
//            override fun onTabUnselected(tab: TabLayout.Tab?) {
//                Log.i("tab - unselected", tab?.text.toString())
//            }
//
//            override fun onTabReselected(tab: TabLayout.Tab?) {
//                Log.i("tab - reselected", tab?.text.toString())
//            }
//        })
    }

//    fun goToFragment(fragment: Fragment) {
//        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
//        fragmentTransaction?.replace(R.id.container, fragment)
//        fragmentTransaction?.commit()
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
package com.lucasesteves.beerloversapp
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(
    private val fragmentsList: List<Fragment>,
    private val fragmentsTitleList: List<String>,
    fragmentManager: FragmentManager
): FragmentPagerAdapter(fragmentManager) {

    override fun getCount() = fragmentsList.size

    override fun getItem(position: Int) = fragmentsList[position]

    override fun getPageTitle(position: Int) = fragmentsTitleList[position]
}
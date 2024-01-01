package com.dangtho.webview.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dangtho.webview.MainActivity
import com.dangtho.webview.databinding.FragmentHomeBinding
import com.dangtho.webview.ui.home.adapter.MyViewPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            val title = arrayOf("HOME", "NOTIFY", "DASHBOAR")
            viewpager.isUserInputEnabled = false
            viewpager.adapter = (activity as? MainActivity)?.let { MyViewPagerAdapter(it) }
            TabLayoutMediator(tabLayout, viewpager) { tab: TabLayout.Tab, position: Int ->
                tab.text = title[position]

            }.attach()
        }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible) {
            (activity as? MainActivity)?.goBack(false)
            (activity as? MainActivity)?.displaySetting(false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
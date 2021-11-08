package com.dangtho.mynote

import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import com.dangtho.mynote.databinding.ActivityMainBinding
import com.dangtho.mynote.view.viewmodel.MainViewModel
import com.dangtho.mynote.view.base.BaseActivity
import com.dangtho.mynote.view.fragment.HomeFragment

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = _binding!!
        setContentView(binding.root)
//        supportFragmentManager.beginTransaction().add(R.id.nav_host_fragment, HomeFragment()).commit()
    }
    override fun setViewModel() {
        viewModel = MainViewModel()
    }

    override fun setBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
    }
}
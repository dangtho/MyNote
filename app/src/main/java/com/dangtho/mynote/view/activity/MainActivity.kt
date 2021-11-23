package com.dangtho.mynote.view.activity

import android.os.Bundle
import androidx.activity.viewModels
import com.dangtho.mynote.databinding.ActivityMainBinding
import com.dangtho.mynote.view.base.BaseActivity
import com.dangtho.mynote.view.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    lateinit var binding: ActivityMainBinding
    private val mViewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = _binding!!
        setContentView(binding.root)
    }

    override fun setViewModel() {
        this.viewModel = mViewModel
    }

    override fun setBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
    }
}
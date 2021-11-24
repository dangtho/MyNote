package com.dangtho.mynote.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import com.dangtho.mynote.databinding.ActivityMainBinding
import com.dangtho.mynote.view.base.BaseActivity
import com.dangtho.mynote.view.debug.ListUrlActivity
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
        setMyToolBar()
        myToolBar?.setOnLongClickListener {
            startActivity(Intent(this, ListUrlActivity::class.java))
            return@setOnLongClickListener false
        }
    }

    override fun setViewModel() {
        this.viewModel = mViewModel
    }

    override fun setBinding() {
        _binding = ActivityMainBinding.inflate(layoutInflater)
    }

    override fun setMyToolBar() {
        myToolBar = binding.myToolBar
    }
}
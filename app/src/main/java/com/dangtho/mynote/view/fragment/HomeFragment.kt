package com.dangtho.mynote.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.dangtho.mynote.R
import com.dangtho.mynote.databinding.FragmentMainBinding
import com.dangtho.mynote.view.base.BaseFragment
import com.dangtho.mynote.view.viewmodel.HomeFragmentViewModel
import kotlinx.coroutines.flow.observeOn

class HomeFragment : BaseFragment<HomeFragmentViewModel>(), View.OnClickListener {
    private var _binding: FragmentMainBinding? = null
    private lateinit var binding: FragmentMainBinding
    override fun setViewModel() {
        viewModel = HomeFragmentViewModel()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        binding = _binding!!
        binding.data = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolBar.imgAddPlace.setOnClickListener(this)
        binding.tvCondition.setOnClickListener(this)
        binding.tvCondition.text = "Có mưa"
    }

    override fun onClick(view: View?) {
        if (view?.id == R.id.tool_bar) {
            viewModel?.getForeCastWeather()
        } else if (view?.id == R.id.tv_condition) {
            viewModel?.getForeCastWeather()
        }
    }
}
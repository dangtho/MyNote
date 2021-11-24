package com.dangtho.mynote.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateViewModelFactory
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import com.dangtho.mynote.R
import com.dangtho.mynote.data.model.base.Status
import com.dangtho.mynote.databinding.FragmentLoginBinding
import com.dangtho.mynote.view.base.BaseFragment
import com.dangtho.mynote.view.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class LoginFragment : BaseFragment<LoginViewModel, FragmentLoginBinding>() {
    private lateinit var binding: FragmentLoginBinding
    private val _viewModel: LoginViewModel by viewModels()
    override fun setContext() {
        mContext = binding.root.context
    }

    override fun setViewModel() {
        viewModel = _viewModel
    }

    override fun setBinding() {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onStop() {
        super.onStop()
        viewModel?.cancelDoLogin()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleToolBar("Login")
        binding.edtEmail.setText(LoginViewModel.EMAIL)
        binding.edtPassword.setText(LoginViewModel.PASSWORD)
        binding.btnLogin.setOnClickListener {
            binding.btnLogin.isEnabled = false
            viewModel?.email?.value = binding.edtEmail.text.toString()
            viewModel?.password?.value = binding.edtPassword.text.toString()
            viewModel?.doLogin()
        }

        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel?.result?.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(
                            mContext,
                            "token ${it.data?.token}",
                            Toast.LENGTH_SHORT
                        ).show()
                        NavHostFragment.findNavController(this@LoginFragment)
                            .navigate(R.id.action_LoginFragment_to_userFragment)
                        viewModel?.cancelDoLogin()
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            mContext,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                        viewModel?.cancelDoLogin()
                    }
                    else -> {
                        // loading
                    }
                }
                binding.btnLogin.isEnabled = true
            }
        }
    }
}
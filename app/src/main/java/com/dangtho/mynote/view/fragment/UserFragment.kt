package com.dangtho.mynote.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dangtho.mynote.data.model.base.Status
import com.dangtho.mynote.databinding.FragmentUserBinding
import com.dangtho.mynote.view.adapter.UsersAdapter
import com.dangtho.mynote.view.base.BaseFragment
import com.dangtho.mynote.view.viewmodel.UserFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class UserFragment : BaseFragment<UserFragmentViewModel, FragmentUserBinding>() {
    private lateinit var binding: FragmentUserBinding
    private val _viewModel: UserFragmentViewModel by viewModels()
    private var adapter: UsersAdapter? = null
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
        _binding = FragmentUserBinding.inflate(inflater)
        binding = _binding!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleToolBar("List User")
        viewModel?.getListUsers()
        setRcvUser()
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel?.result?.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(
                            mContext,
                            "Success",
                            Toast.LENGTH_SHORT
                        ).show()
                        adapter?.setListUser(it.data?.userList)
                    }
                    Status.ERROR -> {
                        Toast.makeText(
                            mContext,
                            it.message,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    else -> {
                        // loading
                    }
                }

            }
        }
    }

    private fun setRcvUser() {
        binding.rcvUsers.layoutManager = LinearLayoutManager(mContext)
        val dividerItemDecoration = DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)
        binding.rcvUsers.addItemDecoration(dividerItemDecoration)
        if (adapter == null) {
            adapter = UsersAdapter()
        }
        binding.rcvUsers.adapter = adapter
    }
}
package com.dangtho.mynote.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.UserEntity
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

    private val listUser: MutableList<UserEntity> = mutableListOf()
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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setTitleToolBar("List User")
        setRcvUser()
//        viewModel?.listPerson?.observe(viewLifecycleOwner, Observer {
//            Toast.makeText(mContext, it.size, Toast.LENGTH_SHORT).show()
//        })
//        viewModel?.getListUserFromDataBase()?.observe(viewLifecycleOwner, Observer {
//            it?.apply {
//                if (isEmpty()) return@Observer
//                val users = it.get(2)
//                val loginResponse = users.loginToken ?: LoginResponse()
//                users.loginToken = loginResponse.apply {
//                    token = "dangtho"
//                }
//                viewModel?.updateUser(users)
//                viewModel?.getTokenUser(users.id)?.observe(viewLifecycleOwner, Observer {
//                    setTitleToolBar(it?.loginToken?.token ?: "")
//                })
//            }
//            listUser.addAll(it)
//            adapter?.setListUser(it)
//        })
        viewLifecycleOwner.lifecycleScope.launchWhenCreated {
            viewModel?.listUser?.collect {
                adapter?.notifyDataSetChanged()
            }
            viewModel?.result?.collect {
                when (it.status) {
                    Status.SUCCESS -> {
                        Toast.makeText(
                            mContext,
                            "Success",
                            Toast.LENGTH_SHORT
                        ).show()
//                        adapter?.setListUser(it.data?.userList)
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

    override fun onStop() {
        super.onStop()
        viewModel?.cancelJob()
    }

    private fun setRcvUser() {
        binding.rcvUsers.layoutManager = LinearLayoutManager(mContext)
        val dividerItemDecoration = DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL)
        binding.rcvUsers.addItemDecoration(dividerItemDecoration)
        if (adapter == null) {
            adapter = UsersAdapter(viewModel!!, viewLifecycleOwner)
            Log.e("Vvvvvvvv", "adapter")
        }
        binding.rcvUsers.adapter = adapter
        Log.e("Vvvvvvvv", "rcvUsers")

    }
}
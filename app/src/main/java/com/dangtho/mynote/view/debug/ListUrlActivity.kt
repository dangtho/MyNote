package com.dangtho.mynote.view.debug

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dangtho.mynote.data.database.UrlEntity
import com.dangtho.mynote.databinding.ActivityListUrlBinding
import com.dangtho.mynote.view.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListUrlActivity : BaseActivity<ListUrLViewModel, ActivityListUrlBinding>() {
    private lateinit var binding: ActivityListUrlBinding
    private var urlAdapter: ListUrlAdapter? = null
    private val mViewModel: ListUrLViewModel by viewModels()
    override fun setViewModel() {
        this.viewModel = mViewModel
    }

    override fun setBinding() {
        _binding = ActivityListUrlBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = _binding!!
        setContentView(binding.root)
        setRcv()
        setDataViewModel()
    }

    private val clickDetailListener = object : Listener {
        override fun onClick(item: UrlEntity?) {
            val intent = Intent(this@ListUrlActivity, DetailUrlResponseActivity::class.java)
            intent.putExtra(DetailUrlResponseActivity.DATA_DETAILS_RESPONES, item?.details)
            startActivity(intent)
        }
    }

    private fun setRcv() {
        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.rcvListUrl2.layoutManager = layoutManager
        val dividerItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        binding.rcvListUrl2.addItemDecoration(dividerItemDecoration)
        if (urlAdapter == null) {
            urlAdapter = ListUrlAdapter()
        }
        binding.rcvListUrl2.adapter = urlAdapter
        urlAdapter?.listener = clickDetailListener
    }

    private fun setDataViewModel() {
        viewModel?.getAllUrl()?.observe(this, Observer {
            it ?: return@Observer
            urlAdapter?.setListUrl(it)
        })
    }

    override fun setMyToolBar() {
        TODO("Not yet implemented")
    }
}
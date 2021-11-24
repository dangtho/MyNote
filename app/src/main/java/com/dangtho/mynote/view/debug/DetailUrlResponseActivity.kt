package com.dangtho.mynote.view.debug

import android.os.Bundle
import androidx.activity.viewModels
import com.dangtho.mynote.databinding.FragmentDetailUrlBinding
import com.dangtho.mynote.view.base.BaseActivity

class DetailUrlResponseActivity :
    BaseActivity<DetailUrlResponseViewModel, FragmentDetailUrlBinding>() {
    companion object {
        const val DATA_DETAILS_RESPONES = "data_detail_reponse"
    }

    private lateinit var binding: FragmentDetailUrlBinding
    private var urlEntityDetails: String? = null
    private val mViewModel: DetailUrlResponseViewModel by viewModels()
    override fun setViewModel() {
        this.viewModel = mViewModel
    }

    override fun setBinding() {
        _binding = FragmentDetailUrlBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = _binding!!
        binding.data = viewModel
        setContentView(binding.root)
        urlEntityDetails = intent?.getStringExtra(DATA_DETAILS_RESPONES)
        viewModel?.urlEntityDetail?.value = urlEntityDetails?.let { formatJson(it) }
    }

    private fun formatJson(json: String): String {
        val stringBuilder = StringBuilder()
        var indent = ""
        json.forEachIndexed { _, c ->
            when (c.toString()) {
                "{" -> {
                    stringBuilder.append("$c")
                    indent = "\t"
                    stringBuilder.append(indent)
                }
                "[" -> {
                    stringBuilder.append(c)
                }
                "]" -> {
                    stringBuilder.append(c)
                }
                "}" -> {
                    indent = indent.replaceFirst("\t", "")
                    stringBuilder.append("\n$c")
                }
                "," -> {
                    stringBuilder.append(c)
                    stringBuilder.append("\n$indent")
                }
                else -> stringBuilder.append("$c")
            }
        }
        return stringBuilder.toString()
    }

    override fun setMyToolBar() {
        TODO("Not yet implemented")
    }
}
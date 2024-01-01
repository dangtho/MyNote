package com.dangtho.webview.ui.dashboard

import android.content.Context
import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.*
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.dangtho.webview.MainActivity
import com.dangtho.webview.R
import com.dangtho.webview.databinding.FragmentDashboardBinding
import com.dangtho.webview.ui.home.HomeFragment

class DashboardFragment(val url: String = "https://www.google.com/") : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.webView.webViewClient = WebClient()
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(url)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setHasOptionsMenu(true)

        binding.imgBack.setOnClickListener {
            if (canGoback()) {
                binding.webView.goBack()
            }
        }
    }

    private fun canGoback(): Boolean {
        return binding.webView.canGoBack()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_toolbar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun onBackPress() {
        if (binding.webView.canGoBack()) {
            binding.webView.goBack()
        }
    }

    inner class WebClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            (activity as? MainActivity)?.goBack(view?.canGoBack() ?: false)
            return super.shouldOverrideUrlLoading(view, request)
        }
    }

    override fun setMenuVisibility(menuVisible: Boolean) {
        super.setMenuVisibility(menuVisible)
        if (menuVisible && !isResumed) {
            (activity as? MainActivity)?.goBack(binding.webView.canGoBack())
            (activity as? MainActivity)?.displaySetting(binding.webView.url == MainActivity.STACK_OVER_FLOW)
            (activity as? MainActivity)?.displaySettingScreen()
        }
    }

}

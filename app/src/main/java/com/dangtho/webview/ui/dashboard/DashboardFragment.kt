package com.dangtho.webview.ui.dashboard

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
import com.dangtho.webview.R
import com.dangtho.webview.databinding.FragmentDashboardBinding
import com.dangtho.webview.ui.home.HomeFragment

class DashboardFragment : Fragment() {

    private lateinit var dashboardViewModel: DashboardViewModel
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

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
        binding.webView.loadUrl("https://www.google.com/")
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        dashboardViewModel.isVisible.observe(viewLifecycleOwner, Observer {
            binding.imgBack.visibility = it
        })
        dashboardViewModel.isDetail.observe(viewLifecycleOwner, {
            if (true) {
                Log.e("mmmmmmmmm", "onViewCreated: $parentFragment")
                ((parentFragment as? NavHostFragment)?.childFragmentManager?.fragments?.get(0) as? HomeFragment)?.noScroll()
            }
        })

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

    inner class WebClient : WebViewClient() {

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            Log.e("CCCC", "onPageStarted: ")
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            Log.e("CCCC", "onPageFinished: ")
            dashboardViewModel._isVisible.value = if (canGoback()) View.VISIBLE else View.GONE
            isShouldOverrideUrlLoading = false

        }

        private var isShouldOverrideUrlLoading: Boolean = false
        override fun shouldOverrideUrlLoading(
            view: WebView?,
            request: WebResourceRequest?
        ): Boolean {
            dashboardViewModel.isDetail.postValue(true)
            Log.e("ccccc", "shouldOverrideUrlLoading: ${request?.url}")
            isShouldOverrideUrlLoading = true
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun doUpdateVisitedHistory(view: WebView?, url: String?, isReload: Boolean) {
            super.doUpdateVisitedHistory(view, url, isReload)
            Log.e("CCCCCCC", "doUpdateVisitedHistory: $url \n $isReload")
            Log.e("CCCCCCC", "doUpdateVisitedHistory: ${view?.url.equals(url)} \n $isReload")
        }

        override fun onPageCommitVisible(view: WebView?, url: String?) {
            if (!isShouldOverrideUrlLoading) {
                Log.e("CCCCC", "onPageCommitVisible: $url")
                Log.e("CCCCC", "onPageCommitVisible: ${view?.url.equals(url)}")
            }
            super.onPageCommitVisible(view, url)
        }
    }
}

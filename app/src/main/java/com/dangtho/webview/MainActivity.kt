package com.dangtho.webview

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.dangtho.webview.databinding.ActivityMainBinding
import com.dangtho.webview.ui.dashboard.DashboardFragment
import com.dangtho.webview.ui.home.HomeFragment
import com.dangtho.webview.ui.home.adapter.MyViewPagerAdapter
import com.dangtho.webview.ui.notifications.NotificationsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val bottomListener = NavigationBarView.OnItemSelectedListener {
        return@OnItemSelectedListener when (it.itemId) {
            R.id.navigation_home -> {
                binding.viewpager.currentItem = 0
                displayContent()
                true
            }

            R.id.navigation_dashboard -> {
                binding.viewpager.currentItem = 1
                displayContent()
                true
            }

            R.id.navigation_notifications -> {
                binding.viewpager.currentItem = 2
                displayContent()
                true
            }

            else -> false
        }
    }

    @SuppressLint("UseSupportActionBar")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.apply {
            navView.setOnItemSelectedListener(bottomListener)
            viewpager.isUserInputEnabled = false
            val frgments = arrayOf(
                DashboardFragment(),
                HomeFragment(),
                DashboardFragment(STACK_OVER_FLOW),
            )
            viewpager.adapter = MyViewPagerAdapter(
                this@MainActivity, frgments
            )
            btnBack.setOnClickListener {
                if (viewpager.currentItem == 2 &&
                    supportFragmentManager.findFragmentByTag("noty") != null
                ) {
                    supportFragmentManager.popBackStack()
                    displayContent(true)
                    goBack(false)
                    displaySetting(true)
                    return@setOnClickListener
                }
                (frgments[viewpager.currentItem] as? DashboardFragment)?.onBackPress()
            }
            btnSetting.setOnClickListener {
                displaySettingScreen(true)
            }

        }
    }

    private fun displayContent(isWebview: Boolean = true) {
        binding.apply {
            viewpager.isVisible = isWebview

            frlContent.isVisible = !isWebview
        }
    }

    fun goBack(canGoBack: Boolean) {
        binding.btnBack.isVisible = canGoBack
    }

    fun displaySetting(isVisible: Boolean = true) {
        binding.btnSetting.isVisible = isVisible
    }

    fun displaySettingScreen(
        isOpenSettingScreen: Boolean = binding.viewpager.currentItem == 2 &&
                supportFragmentManager.backStackEntryCount > 0
    ) {
        if (!isOpenSettingScreen) return

        supportFragmentManager.beginTransaction()
            .replace(R.id.frl_content, NotificationsFragment(), "noty")
            .addToBackStack(null)
            .commit()
        goBack(true)
        displaySetting(false)
        displayContent(false)
    }

    companion object {
        const val STACK_OVER_FLOW = "https://stackoverflow.com/"
    }
}
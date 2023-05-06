package com.dangtho.webview.ui.home.adapter

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dangtho.webview.ui.dashboard.DashboardFragment
import com.dangtho.webview.ui.notifications.NotificationsFragment

class MyViewPagerAdapter(frafmentManager: FragmentManager) : FragmentPagerAdapter(frafmentManager) {

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "Home"
            1 -> "DashBoard"
            else -> "Notify"
        }
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> DashboardFragment()
            1 -> NotificationsFragment()
            else -> DashboardFragment()
        }
    }
}
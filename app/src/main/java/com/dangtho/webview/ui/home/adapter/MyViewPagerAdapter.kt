package com.dangtho.webview.ui.home.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.dangtho.webview.databinding.ActivityMainBinding
import com.dangtho.webview.ui.dashboard.DashboardFragment
import com.dangtho.webview.ui.notifications.NotificationsFragment

class MyViewPagerAdapter(
    activity: AppCompatActivity, val fragment: Array<Fragment> = arrayOf(
        DashboardFragment(),
        NotificationsFragment(),
        DashboardFragment()
    )
) :
    FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return fragment[position]
    }
}
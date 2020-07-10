package com.fauzan.githubusers.adapter

import android.content.Context
import androidx.annotation.Nullable
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fauzan.githubusers.R
import com.fauzan.githubusers.ui.fragment.FollowersFragment
import com.fauzan.githubusers.ui.fragment.FollowingFragment

class SectionPagerAdapter(private val mContext: Context, fragmentManager: FragmentManager) :
    FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    @StringRes
    private val TITLE = intArrayOf(R.string.followers, R.string.following)

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment()
            1 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }

    @Nullable
    override fun getPageTitle(position: Int): CharSequence? {
        return mContext.resources.getString(TITLE[position])
    }

    override fun getCount(): Int {
        return 2
    }


}
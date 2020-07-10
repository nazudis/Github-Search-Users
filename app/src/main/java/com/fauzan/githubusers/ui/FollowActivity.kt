package com.fauzan.githubusers.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.fauzan.githubusers.R
import com.fauzan.githubusers.adapter.SectionPagerAdapter
import kotlinx.android.synthetic.main.activity_follow.*

class FollowActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LOGIN = "extra_login"
        const val EXTRA_PAGE = "extra_page"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_follow)

        //Get Login/username from DetailActivity
        val name = intent.getStringExtra(EXTRA_LOGIN)

        //Set up Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar_follow)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) {
            title = name
            toolbar.setNavigationIcon(R.drawable.ic_arrow_back)
            toolbar.setNavigationOnClickListener { finish() }
        }

        //Set Up TabLayout Followers and Following, SectionPagerAdapter
        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager)
        view_pager.adapter = sectionPagerAdapter
        tab_layout.setupWithViewPager(view_pager)

        //Set Spesific Tab, so if user tap the following, it will be spesific go to the FollowingFragment (ViewPager Page 1)
        val defaultPage = 0
        val page = intent.getIntExtra(EXTRA_PAGE, defaultPage)
        view_pager.currentItem = page
    }
}
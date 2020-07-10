package com.fauzan.githubusers.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauzan.githubusers.R
import com.fauzan.githubusers.adapter.SearchListUserAdapter
import com.fauzan.githubusers.model.UserList
import com.fauzan.githubusers.viewmodel.SearchUserViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: SearchListUserAdapter
    private lateinit var searchViewModel: SearchUserViewModel
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Set up Toolbar
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        if (supportActionBar != null) title = null

        //Method to Get ViewModel SearchListUser
        searchViewModel = ViewModelProvider(
            this, ViewModelProvider.NewInstanceFactory()
        ).get(SearchUserViewModel::class.java)

        //Observe Change
        searchViewModel.getSearchResult().observe(this, Observer { user ->
            if (!user.isNullOrEmpty()) {
                adapter.setData(user)
                loading(false)
            } else {
                adapter.setData(user)
                Toast.makeText(this, "User not found", Toast.LENGTH_SHORT).show()
                loading(false)
            }
        })

        //Setting Search Query
        searchView = findViewById(R.id.search_view)
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String): Boolean {
                //Send data username
                if (query.isNotEmpty()) {
                    searchViewModel.setSearchUser(query)
                    loading(true)
                    rv_gituser.visibility = View.VISIBLE
                }
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                if (newText.isEmpty()) {
                    rv_gituser.visibility = View.GONE
                }
                return true
            }
        })

        showRecyclerView()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.about -> {
                val moveToAbout = Intent(this, AboutActivity::class.java)
                startActivity(moveToAbout)
                true
            }
            else -> true
        }
    }

    //Method for showing RecylerView from adapter to this Activity
    private fun showRecyclerView() {
        adapter = SearchListUserAdapter()
        adapter.notifyDataSetChanged()

        rv_gituser.adapter = adapter
        rv_gituser.layoutManager = LinearLayoutManager(this)

        adapter.setOnItemClickCallback(object : SearchListUserAdapter.OnItemClickCallback {
            override fun onItemClicked(data: UserList) {
            }

        })
    }

    private fun loading(state: Boolean) {
        if (state) progressBar.visibility = View.VISIBLE
        else progressBar.visibility = View.INVISIBLE
    }
}


package com.veronica.idn.githubappfirstsubmission

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.veronica.idn.githubappfirstsubmission.activity.DetailActivity
import com.veronica.idn.githubappfirstsubmission.adapter.ItemAdapter
import com.veronica.idn.githubappfirstsubmission.databinding.ActivityMainBinding
import com.veronica.idn.githubappfirstsubmission.model.DataUser
import com.veronica.idn.githubappfirstsubmission.model.Users
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private var list = ArrayList<Users>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        mainBinding.rvUsers.setHasFixedSize(true)
        getItemUsersData()
        showRecyclerUser()
    }

    private fun showRecyclerUser() {
        val listItemUserAdapter = ItemAdapter(list)
        mainBinding.rvUsers.layoutManager = LinearLayoutManager(this)
        mainBinding.rvUsers.adapter = listItemUserAdapter

        listItemUserAdapter.setOnItemClickCallback(object : ItemAdapter.OnItemClickCallback {
            override fun onItemClicked(user: Users) {
                setSelectedUser(user)
            }

        })
    }

    private fun setSelectedUser(user: Users) {
        val intentDetail = Intent(this@MainActivity, DetailActivity::class.java)
        intentDetail.putExtra(DetailActivity.EXTRA_USER, user)
        startActivity(intentDetail)
    }

    private fun getItemUsersData() {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "githubuser.json")
        val gson = Gson()
        val data: DataUser = gson.fromJson(jsonFileString, DataUser::class.java)
        list = data.users
    }

    private fun getJsonDataFromAsset(context: Context, dataFile: String): String? {
        val jsonString: String
        try {
            jsonString = context.assets.open(dataFile).bufferedReader().use {
                it.readText()
            }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return jsonString

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        val searchMenu = menu?.findItem(R.id.icSearch)
        searchMenu?.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
            override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
                return true
            }

            override fun onMenuItemActionExpand(item: MenuItem?): Boolean {
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    companion object {
        fun getLaunchService(from: Context) = Intent(from, MainActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        }
    }
}
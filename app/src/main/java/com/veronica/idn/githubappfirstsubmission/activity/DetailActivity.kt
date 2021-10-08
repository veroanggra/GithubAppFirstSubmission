package com.veronica.idn.githubappfirstsubmission.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.veronica.idn.githubappfirstsubmission.databinding.ActivityDetailBinding
import com.veronica.idn.githubappfirstsubmission.model.Users

class DetailActivity : AppCompatActivity() {
    private lateinit var detailBinding: ActivityDetailBinding
    private lateinit var users: Users

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailBinding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(detailBinding.root)

        users = intent.getParcelableExtra<Users>(EXTRA_USER) as Users

        supportActionBar?.title = users.name
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showDetailUser()
    }

    private fun showDetailUser() {
        Glide.with(this).load(users.avatar).circleCrop().into(detailBinding.ivDetailUser)
        detailBinding.tvFollower.text = users.follower.toString()
        detailBinding.tvFollowing.text = users.following.toString()
        detailBinding.tvNameDetail.text = users.name
        detailBinding.tvUsernameDetail.text = users.username
        detailBinding.tvCompanyDetail.text = users.company
        detailBinding.tvLocation.text = users.location

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        const val EXTRA_USER = "extra_user"
    }
}
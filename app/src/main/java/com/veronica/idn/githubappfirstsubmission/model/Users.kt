package com.veronica.idn.githubappfirstsubmission.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Users(
    val username : String,
    val name : String,
    val avatar : String,
    val company: String,
    val location: String,
    val repository: Int,
    val follower: Int,
    val following: Int,
):Parcelable

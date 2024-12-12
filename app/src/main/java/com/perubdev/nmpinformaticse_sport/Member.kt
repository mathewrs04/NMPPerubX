package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Member(var idmember: Int,
                    var fname: String,
                    var lname: String,
                    var username: String,
                    var password: String): Parcelable

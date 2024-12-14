package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Achievement(var idachievement: Int,
                       var idteam: Int,
                       var name: String,
                       var date: String,
                       var description: String) : Parcelable
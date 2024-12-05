package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Team(var idteam: Int,
                var idgame: Int,
                var gameimg: String,
                var name: String): Parcelable

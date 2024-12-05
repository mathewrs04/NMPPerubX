package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Game(var idgame: Int,
                var name: String,
                var description: String,
                var img:String): Parcelable

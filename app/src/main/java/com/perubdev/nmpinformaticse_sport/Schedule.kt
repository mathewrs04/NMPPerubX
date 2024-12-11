package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule(var idevent: Int,
                    var name: String,
                    var date: String,
                    var description: String,
                    var img: String): Parcelable

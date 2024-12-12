package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule(var idevent: Int,
                    var event_name: String,
                    var team_name: String,
                    var date: String,
                    var place: String,
                    var time : String,
                    var description: String,
                    var img: String): Parcelable

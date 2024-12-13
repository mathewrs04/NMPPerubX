package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class wwa(var id: Int,
                var description: String,
                var likes: Int,
                var url:String): Parcelable
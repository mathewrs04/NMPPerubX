package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PlayerBank(
    var name: String,
    var role: String,
    var profileImageId: Int
): Parcelable

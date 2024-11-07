package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TeamBank(
    var name: String,
    var members: Array<PlayerBank>
): Parcelable

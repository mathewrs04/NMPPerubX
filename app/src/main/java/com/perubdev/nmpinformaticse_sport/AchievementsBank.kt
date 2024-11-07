package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AchievementsBank (
    var game: String,
    var achievements: String,
    var year: Int,
    var team: String
): Parcelable
package com.perubdev.nmpinformaticse_sport

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class GameBank (
    var game: String,
    var description: String,
    var achievements: Array<AchievementsBank>,
    var teams: Array<TeamBank>,
    var imageId: Int
): Parcelable
package com.perubdev.nmpinformaticse_sport

data class GameBank (
    var game: String,
    var description: String,
    var achievements: Array<AchievementsBank>,
    var teams: Array<TeamBank>,
    var imageId: Int
)
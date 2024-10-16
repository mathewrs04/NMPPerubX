package com.perubdev.nmpinformaticse_sport

data class GameBank (
    var game: String,
    var description: String,
    var achievements: List<AchievementsBank>,
    var teams: List<TeamBank>,
    var imageId: Int
)
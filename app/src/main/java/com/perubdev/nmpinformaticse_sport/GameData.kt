package com.perubdev.nmpinformaticse_sport

object GameData {
    var games: Array<GameBank> = arrayOf(
        GameBank(
            "Valorant",
            "Tactical shooter game.",
            listOf(
                AchievementsBank("Valorant", "Champion in VCT 2023", 2023, "Team A"),
                AchievementsBank("Valorant", "Runner-up in VCT 2022", 2022, "Team A"),
                AchievementsBank("Valorant", "Top 4 in VCT 2021", 2021, "Team A")
            ),
            listOf(
                TeamMemberBank("Team A", listOf(
                    PlayerBank("Player1", "Duelist", R.drawable.vct),
                    PlayerBank("Player2", "Controller", R.drawable.vct),
                    PlayerBank("Player3", "Sentinel", R.drawable.vct),
                    PlayerBank("Player4", "Initiator", R.drawable.vct),
                    PlayerBank("Player5", "Flex", R.drawable.vct)
                )),
                TeamMemberBank("Team B", listOf(
                    PlayerBank("Player6", "Duelist", R.drawable.vct),
                    PlayerBank("Player7", "Controller", R.drawable.vct),
                    PlayerBank("Player8", "Sentinel", R.drawable.vct),
                    PlayerBank("Player9", "Initiator", R.drawable.vct),
                    PlayerBank("Player10", "Flex", R.drawable.vct)
                ))
            ),
            R.drawable.valorant // Gambar untuk Valorant
        ),
        GameBank(
            "PUBG",
            "Battle royale game.",
            listOf(
                AchievementsBank("PUBG", "Champion in PGC 2024", 2024, "Team B"),
                AchievementsBank("PUBG", "Runner-up in PGC 2023", 2023, "Team B")
            ),
            listOf(
                TeamMemberBank("Team B", listOf(
                    PlayerBank("PlayerA", "Sniper", R.drawable.vct),
                    PlayerBank("PlayerB", "Assault", R.drawable.vct),
                    PlayerBank("PlayerC", "Support", R.drawable.vct),
                    PlayerBank("PlayerD", "Medic", R.drawable.vct)
                )),
                TeamMemberBank("Team C", listOf(
                    PlayerBank("PlayerE", "Sniper", R.drawable.vct),
                    PlayerBank("PlayerF", "Assault", R.drawable.vct),
                    PlayerBank("PlayerG", "Support", R.drawable.vct),
                    PlayerBank("PlayerH", "Medic", R.drawable.vct)
                ))
            ),
            R.drawable.vct
        )
    )
}

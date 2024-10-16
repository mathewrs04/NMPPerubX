package com.perubdev.nmpinformaticse_sport

object GameData {
    var games: Array<GameBank> = arrayOf(
        GameBank(
            "Valorant",
            "Tactical shooter game.",
            arrayOf(
                AchievementsBank("Valorant", "Champion in VCT", 2023, "Team A"),
                AchievementsBank("Valorant", "Runner-up in VCT", 2022, "Team A"),
                AchievementsBank("Valorant", "Top 4 in VCT", 2021, "Team B"),
                AchievementsBank("Valorant", "Champion 1 in VCT", 2024, "Team B"),
                AchievementsBank("Valorant", "Top 5 3 IN VCT", 2023, "Team A")
            ),
            arrayOf(
                TeamBank("Team A", arrayOf(
                    PlayerBank("Player1", "Duelist", R.drawable.vct),
                    PlayerBank("Player2", "Controller", R.drawable.vct),
                    PlayerBank("Player3", "Sentinel", R.drawable.vct),
                    PlayerBank("Player4", "Initiator", R.drawable.vct),
                    PlayerBank("Player5", "Flex", R.drawable.vct)
                )),
                TeamBank("Team B", arrayOf(
                    PlayerBank("Player6", "Duelist", R.drawable.vct),
                    PlayerBank("Player7", "Controller", R.drawable.vct),
                    PlayerBank("Player8", "Sentinel", R.drawable.vct),
                    PlayerBank("Player9", "Initiator", R.drawable.vct),
                    PlayerBank("Player10", "Flex", R.drawable.vct)
                ))
            ),
            R.drawable.valorant
        ),
        GameBank(
            "PUBG",
            "Battle royale game.",
            arrayOf(
                AchievementsBank("PUBG", "Champion in PGC", 2024, "Team A"),
                AchievementsBank("PUBG", "Runner-up in PGC", 2023, "Team B"),
                AchievementsBank("PUBG", "Top 5 in PGC", 2024, "Team A"),
                AchievementsBank("PUBG", "Top 4 in PGC", 2023, "Team B")
            ),
            arrayOf(
                TeamBank("Team B", arrayOf(
                    PlayerBank("PlayerA", "Sniper", R.drawable.vct),
                    PlayerBank("PlayerB", "Assault", R.drawable.vct),
                    PlayerBank("PlayerC", "Support", R.drawable.vct),
                    PlayerBank("PlayerD", "Medic", R.drawable.vct)
                )),
                TeamBank("Team C", arrayOf(
                    PlayerBank("PlayerE", "Sniper", R.drawable.vct),
                    PlayerBank("PlayerF", "Assault", R.drawable.vct),
                    PlayerBank("PlayerG", "Support", R.drawable.vct),
                    PlayerBank("PlayerH", "Medic", R.drawable.vct)
                ))
            ),
            R.drawable.pgc
        )
    )
}

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
                    PlayerBank("ANGE1", "Duelist", R.drawable.player),
                    PlayerBank("SHAO", "Controller", R.drawable.player),
                    PlayerBank("ERIK", "Sentinel", R.drawable.player),
                    PlayerBank("LATEKX", "Initiator", R.drawable.player),
                    PlayerBank("SUYGETSU", "Flex", R.drawable.player)
                )),
                TeamBank("Team B", arrayOf(
                    PlayerBank("ZYPPAN", "Duelist", R.drawable.player),
                    PlayerBank("PARIGIE", "Controller", R.drawable.player),
                    PlayerBank("WESTON", "Sentinel", R.drawable.player),
                    PlayerBank("SUITEZ", "Initiator", R.drawable.player),
                    PlayerBank("TENZ", "Flex", R.drawable.player)
                ))
            ),
            R.drawable.vct
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
                    PlayerBank("AXZCER", "Sniper", R.drawable.player),
                    PlayerBank("PSIX", "Assault", R.drawable.player),
                    PlayerBank("PUNCHER", "Support", R.drawable.player),
                    PlayerBank("LEMON", "Medic", R.drawable.player)
                )),
                TeamBank("Team C", arrayOf(
                    PlayerBank("FLIP", "Sniper", R.drawable.player),
                    PlayerBank("CHEPALENKO", "Assault", R.drawable.player),
                    PlayerBank("YELMENBET", "Support", R.drawable.player),
                    PlayerBank("OMAROV", "Medic", R.drawable.player)
                ))
            ),
            R.drawable.pgc
        ),
        GameBank(
            "Dota 2",
            "Multiplayer online battle arena (MOBA)",
            arrayOf(
                AchievementsBank("PUBG", "Champion in COMPEDIUM", 2024, "Team A"),
                AchievementsBank("PUBG", "The International", 2021, "Team B"),
                AchievementsBank("PUBG", "DreamLeague", 2022 ,"Team A"),
                AchievementsBank("PUBG", "CCT Series 3", 2023, "Team B")
            ),
            arrayOf(
                TeamBank("Team D", arrayOf(
                    PlayerBank("CEB", "POS 1", R.drawable.player),
                    PlayerBank("WISPER", "POS 2", R.drawable.player),
                    PlayerBank("ARI", "POS 3", R.drawable.player),
                    PlayerBank("BZM", "POS 4", R.drawable.player),
                    PlayerBank("KOZAK", "POS 5", R.drawable.player)

                )),
                TeamBank("Team E", arrayOf(
                    PlayerBank("PPSAREL", "POS 1", R.drawable.player),
                    PlayerBank("IZPAH", "POS 2", R.drawable.player),
                    PlayerBank("SOCKSHKA", "POS 3", R.drawable.player),
                    PlayerBank("CHU", "POS 4", R.drawable.player),
                    PlayerBank("LUNA", "POS 5", R.drawable.player)
                ))
            ),
            R.drawable.dota
        )

    )
}

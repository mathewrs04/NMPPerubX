package com.perubdev.nmpinformaticse_sport

object GameAchievements {
    var gameAchievements = arrayOf(
        arrayOf(
            "Valorant", R.drawable.valorant, // Nama game dan drawable untuk gambar
            arrayOf(
                arrayOf("2023", arrayOf("Won XYZ Tournament", "Top 3 in ABC Event")),
                arrayOf("2024", arrayOf("Champion of DEF Tournament", "Runner-up in GHI Event"))
            )
        ),
        arrayOf(
            "Game2", null, // Game tanpa gambar
            arrayOf(
                arrayOf("2023", arrayOf("Reached semi-final in Tournament A", "3rd place in League B")),
                arrayOf("2024", arrayOf("Winner of Tournament C", "Top 8 in League D"))
            )
        )
    )
}


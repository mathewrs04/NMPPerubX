package com.perubdev.nmpinformaticse_sport

object ScheduleData {
    var schedules: Array<ScheduleBank> = arrayOf(
        ScheduleBank(
            "Valorant Champions Tour",
            "24 June",
            "Ryadh, Dubai",
            R.drawable.vct,
            "18:00",
            "Team A",
            "The Valorant Champions Tour is the season finale event for the best teams in the world."
        ),
        ScheduleBank(
            "League of Legends World Championship",
            "16 September",
            "Berlin, Germany",
            R.drawable.worlds,
            "20:00",
            "Team X",
            "The biggest event in League of Legends esports, featuring the best teams."
        ),
        ScheduleBank(
            "The International Compendium",
            "24 September",
            "Copenhagen, Denmark",
            R.drawable.compedium,
            "15:45",
            "Team C",
            "The premier Dota 2 tournament where top teams battle for glory."
        ),
        ScheduleBank(
            "PUBG Global Championship",
            "12 December",
            "Kuala Lumpur, Malaysia",
            R.drawable.pgc,
            "08:00",
            "Team E ",
            "The ultimate competition in PUBG esports, bringing together top teams from across the world."
        )
    )
}

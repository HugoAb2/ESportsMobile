package com.example.esportsmobile.dao

import com.example.esportsmobile.R
import com.example.esportsmobile.model.League

class LeagueDataSource {

    companion object{

        fun startDataSet(): MutableList<League>{

            val leagueList : MutableList<League> = ArrayList()

            leagueList.add(
                League(
                    "1",
                    "CBLOL",
                    "Brazil",
                    "2022 SPLIT 2",
                    "22:51",
                    "32:52",
                    "47:41",
                    "26",
                    R.drawable.league_logo_cblol)
            )

            leagueList.add(
                League(
                    "2",
                    "LCK",
                    "Korea",
                    "2022 SUMMER",
                    "19:95",
                    "32:58",
                    "50:36",
                    "22",
                    R.drawable.league_logo_lck)
            )

            leagueList.add(
                League(
                    "3",
                    "LCS",
                    "EUA",
                    "2022 SUMMER",
                    "24:33",
                    "33:02",
                    "51:37",
                    "23",
                    R.drawable.league_logo_lcs)
            )

            leagueList.add(
                League(
                    "4",
                    "LEC",
                    "Europe",
                    "2022 SUMMER",
                    "21:07",
                    "33:32",
                    "54:04",
                    "25",
                    R.drawable.league_logo_lec)
            )

            leagueList.add(
                League(
                    "5",
                    "LPL",
                    "China",
                    "2022 SUMMER",
                    "21:25",
                    "31:16",
                    "56:03",
                    "26",
                    R.drawable.league_logo_lpl)
            )

            leagueList.add(
                League(
                    "6",
                    "WORLDS",
                    "All",
                    "2022",
                    "22:51",
                    "31:40",
                    "46:11",
                    "25",
                    R.drawable.league_logo_worlds)
            )

            return leagueList
        }
    }
}
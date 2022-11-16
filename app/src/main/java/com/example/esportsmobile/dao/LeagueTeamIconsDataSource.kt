package com.example.esportsmobile.dao

import com.example.esportsmobile.R
import com.example.esportsmobile.model.TeamIcon

class LeagueTeamIconsDataSource {

    companion object{

        fun createCBLOLDataSet() : ArrayList<TeamIcon>{

            val list = ArrayList<TeamIcon>()

            list.add(
                TeamIcon(
                    R.drawable.team_logo_loud,
                "Loud")
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_pain,
                    "Pain"
                )
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_furia,
                    "Furia"
                )
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_intz,
                    "INTZ"
                )
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_miners,
                    "Miners"
                )
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_kabum,
                    "Kabum"
                )
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_flamengo,
                    "Flamengo"
                )
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_liberty,
                    "Liberty"
                )
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_rensga,
                    "Rensga"
                )
            )

            list.add(
                TeamIcon(
                    R.drawable.team_logo_red_canids,
                    "Red Canids"
                )
            )

            return list
        }

    }
}
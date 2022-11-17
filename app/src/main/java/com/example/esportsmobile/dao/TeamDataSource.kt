package com.example.esportsmobile.dao

import com.example.esportsmobile.R
import com.example.esportsmobile.model.Team

class TeamDataSource {

    companion object{

        fun startTeamsDataSource() : ArrayList<Team> {
            val teamsData = ArrayList<Team>()

            teamsData.add(Team("Loud",
                "Brazil",
                "12/18",
                "16.7",
                "11.4",
                "33:17",
                R.drawable.team_logo_loud))

            teamsData.add(Team("Pain",
                "Brazil",
                "12/18",
                "14.4",
                "11.3",
                "33:41",
                R.drawable.team_logo_pain))

            teamsData.add(Team("Furia",
                "Brazil",
                "15/18",
                "14.9",
                "9.8",
                "33:40",
                R.drawable.team_logo_furia))

            teamsData.add(Team("INTZ",
                "Brazil",
                "4/18",
                "10.7",
                "17.4",
                "33:35",
                R.drawable.team_logo_intz))

            teamsData.add(Team("Miners",
                "Brazil",
                "8/18",
                "10.8",
                "13.2",
                "33:52",
                R.drawable.team_logo_miners))

            teamsData.add(Team("Kabum",
                "Brazil",
                "11/18",
                "15.5",
                "11.7",
                "30:29",
                R.drawable.team_logo_kabum))

            teamsData.add(Team("Flamengo",
                "Brazil",
                "6/18",
                "11.8",
                "14.7",
                "33:43",
                R.drawable.team_logo_flamengo))

            teamsData.add(Team("Liberty",
                "Brazil",
                "8/18",
                "12.2",
                "12.3",
                "32:49",
                R.drawable.team_logo_liberty))

            teamsData.add(Team("Rensga",
                "Brazil",
                "1/18",
                "7.7",
                "17.6",
                "30:4",
                R.drawable.team_logo_rensga))

            teamsData.add(Team("Red Canids",
                "Brazil",
                "13/18",
                "16.7",
                "12.3",
                "32:57",
                R.drawable.team_logo_red_canids))

            return teamsData
        }
    }
}
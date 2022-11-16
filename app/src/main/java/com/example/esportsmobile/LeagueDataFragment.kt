package com.example.esportsmobile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.example.esportsmobile.dao.LeagueDataSource
import com.example.esportsmobile.databinding.FragmentLeagueDataBinding
import com.example.esportsmobile.model.League


class LeagueDataFragment : Fragment(R.layout.fragment_league_data) {

    companion object{
        private const val TAG = "resposta"
    }

    private lateinit var binding : FragmentLeagueDataBinding

    private lateinit var name : String
    private lateinit var league: League

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        league = searchLeague()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLeagueDataBinding.bind(view)
        initPageData()

    }

    private fun initPageData(){
        binding.leagueLogo.setImageResource(league.icon)
        binding.leagueName.text = league.name
        binding.region.text = league.region
        binding.season.text = league.season
        binding.shortestGame.text = league.shortestGame
        binding.averageGame.text = league.averageGameTime
        binding.longestGame.text = league.longestGame
        binding.averageKill.text = league.averageGameKills

    }

    private fun searchLeague(): League{
        return LeagueDataSource.startDataSet().find { it.name == name }!!
    }

    fun receiveLeague(leagueName : String){
        name = leagueName
        Log.d(TAG, "receiveLeague: $name")
    }

}
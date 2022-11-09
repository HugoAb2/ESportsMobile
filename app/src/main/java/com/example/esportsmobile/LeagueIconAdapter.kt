package com.example.esportsmobile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.LeagueItemBinding

class LeagueIconAdapter(private val leagueLeagueIconList: MutableList<LeagueIcon>) : RecyclerView.Adapter<LeagueIconAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val leagueIconView = LeagueItemBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
        return MyViewHolder(leagueIconView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val league = leagueLeagueIconList[position]
        holder.logo.setImageResource(league.image)
        holder.name.text = league.name
    }

    override fun getItemCount(): Int {
        return leagueLeagueIconList.size
    }

    class MyViewHolder (binding : LeagueItemBinding): RecyclerView.ViewHolder(binding.root){
            val logo = binding.leagueLogo
            val name = binding.leagueName
    }

}
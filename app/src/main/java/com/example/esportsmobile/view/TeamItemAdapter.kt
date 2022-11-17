package com.example.esportsmobile.view

import com.example.esportsmobile.databinding.ItemTeamBinding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.model.TeamIcon

class TeamItemAdapter(private val teamItemList: MutableList<TeamIcon>, private val onItemClicked: (TeamIcon)->Unit) : RecyclerView.Adapter<TeamItemAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val teamItemView = ItemTeamBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
        return MyViewHolder(teamItemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val team = teamItemList[position]
        holder.apply {
            logo.setImageResource(team.logo)
            name.text = team.name
            layout.setOnClickListener{
                onItemClicked(team)
            }
        }



    }

    override fun getItemCount(): Int {
        return teamItemList.size
    }

    class MyViewHolder (binding : ItemTeamBinding): RecyclerView.ViewHolder(binding.root){
        val logo = binding.teamLogo
        val name = binding.teamName
        val layout = binding.layout


    }

}
package com.example.esportsmobile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.ItemLeagueBinding
import com.example.esportsmobile.model.LeagueIcon

class LeagueIconAdapter(private val leagueLeagueIconList: MutableList<LeagueIcon>) : RecyclerView.Adapter<LeagueIconAdapter.MyViewHolder>() {

    private lateinit var itemListener : OnItemClickListener

    interface OnItemClickListener{

        fun onItemClick(position: Int)

    }

    fun setOnItemClickListener(listener : OnItemClickListener){
        itemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val leagueIconView = ItemLeagueBinding.inflate(
            LayoutInflater.from(parent.context),parent, false
        )
        return MyViewHolder(leagueIconView, itemListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val league = leagueLeagueIconList[position]
        holder.apply {
            logo.setImageResource(league.image)
            name.text = league.name
        }
    }

    override fun getItemCount(): Int {
        return leagueLeagueIconList.size
    }

    class MyViewHolder (binding : ItemLeagueBinding, listener: OnItemClickListener): RecyclerView.ViewHolder(binding.root){
        val logo = binding.leagueLogo
        val name = binding.leagueName
        val layout = binding.leagueIcon

        init{
            binding.root.setOnClickListener{
                listener.onItemClick(adapterPosition)
            }
        }
    }

}
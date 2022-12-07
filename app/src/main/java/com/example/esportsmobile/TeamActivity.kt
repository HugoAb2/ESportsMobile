package com.example.esportsmobile

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.esportsmobile.dao.TeamDataSource
import com.example.esportsmobile.databinding.ActivityTeamBinding
import com.example.esportsmobile.model.Team

class TeamActivity : AppCompatActivity() {

    private lateinit var binding : ActivityTeamBinding

    private lateinit var team : Team
    private lateinit var teamName : String
    private lateinit var showComments : Button
    private lateinit var addComment: Button

    private lateinit var teamList: MutableList<Team>

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show()
        }
        if (it.resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this, "Comment didn't published", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showComments = binding.showComments
        addComment = binding.addComments

        teamName = intent.getStringExtra("name") as String

        getTeamData()
        startPage()
        buttonConfig()
    }

    private fun startPage(){
        binding.apply {
            backIcon.setOnClickListener { finish()}
            teamLogo.setImageResource(team.logo)
            teamName.text = team.name
            region.text = team.region
            winRate.text = team.winRate
            averageGameKills.text = team.averageKillsGame
            averageGameDeath.text = team.averageDeathsGame
            averageGameTime.text = team.averageGameTime
        }
    }

    private fun buttonConfig(){
        showComments.setOnClickListener{
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra("target", teamName)
            intent.putExtra("operation", "list")
            intent.putExtra("userComments", false)
            launcher.launch(intent)
        }

        addComment.setOnClickListener {
            val intent = Intent(this, CommentActivity::class.java)
            intent.putExtra("target", teamName)
            intent.putExtra("operation", "add")
            launcher.launch(intent)
        }
    }

    private fun getTeamData(){
        teamList = TeamDataSource.startTeamsDataSource()
        team = teamList.find { it.name == teamName } as Team
    }

}
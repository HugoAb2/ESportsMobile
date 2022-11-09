package com.example.esportsmobile

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.FragmentContainerView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.esportsmobile.databinding.ActivityHomeBinding
import com.example.esportsmobile.model.LeagueIcon
import com.example.esportsmobile.model.User
import com.example.esportsmobile.view.LeagueIconAdapter

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private lateinit var topBar : FragmentContainerView

    private lateinit var welcomeText : TextView
    private lateinit var leagueListView : RecyclerView
    private lateinit var leagueIconAdapter : LeagueIconAdapter
    private lateinit var userEmail : String

    private var leagueIconList : MutableList<LeagueIcon> = ArrayList()
    private var userList : MutableList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userList.add(User("Admin", 18, "Brazil", "admin", "*****"))
        userEmail = intent.getStringExtra("user_email").toString()
        init()
    }

    override fun onStart() {
        super.onStart()
        //do something with userEmail to show a name in my View
        welcomeText = binding.emphasisMessage
        val welcomeMessage = "Welcome my Lord${findUserName()}!"
        welcomeText.text = welcomeMessage
    }

    private fun findUserName(): String{
        val user = userList.find { userEmail == it.email }
        return if(user == null){
            ""
        }else{
            " ${user.name}"
        }
    }

    private fun init(){
        leagueListView = binding.recycleView
        leagueListView.setHasFixedSize(true)
        leagueListView.layoutManager = GridLayoutManager(this, 2)

        leagueIconList = ArrayList()
        addDataToList()

        leagueIconAdapter = LeagueIconAdapter(leagueIconList)
        leagueListView.adapter = leagueIconAdapter

    }

    override fun onResume() {
        super.onResume()

        leagueIconAdapter.setOnItemClickListener(object : LeagueIconAdapter.OnItemClickListener{
            override fun onItemClick(position: Int) {
                val intent = Intent(this@HomeActivity, LeagueActivity::class.java)
                val chosenLeague = leagueIconList[position]
                intent.putExtra("league_name", chosenLeague.name)
                startActivity(intent)
            }
        })
    }



    private fun addDataToList(){
        leagueIconList.add(LeagueIcon(R.drawable.logo_cblol, "CBLOL"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_lck, "LCK"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_lcs, "LCS"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_lec, "LEC"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_lpl, "LPL"))
        leagueIconList.add(LeagueIcon(R.drawable.logo_worlds, "WORLDS"))
    }
}
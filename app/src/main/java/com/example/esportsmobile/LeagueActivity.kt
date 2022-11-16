package com.example.esportsmobile

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.esportsmobile.databinding.ActivityLeagueBinding
import com.example.esportsmobile.model.User
import com.example.esportsmobile.view.PageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LeagueActivity : DrawerBaseActivity() {
    
    private lateinit var binding : ActivityLeagueBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var user: User
    private lateinit var tittle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeagueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        user = intent.getSerializableExtra("user") as User
        tittle = intent.getStringExtra("league_name") as String

        implementsNavDrawer(user, tittle)

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        configTabLayout()
    }

    private fun configTabLayout(){
        val adapter = PageAdapter(this)
        viewPager.adapter = adapter

        val leagueDataFragment = LeagueDataFragment()
        val leagueTeamsFragment = LeagueTeamsFragment()

        leagueDataFragment.receiveLeague(tittle)
        leagueTeamsFragment.receiveData(tittle, user)

        adapter.addFragment(leagueDataFragment, "Overview")
        adapter.addFragment(leagueTeamsFragment, "Teams")

        viewPager.offscreenPageLimit = adapter.itemCount
        val mediator = TabLayoutMediator(
            tabLayout, viewPager){
                tab : TabLayout.Tab, position : Int ->
            tab.text = adapter.getTitle(position)
        }
        mediator.attach()
    }

    private fun implementsNavDrawer(user: User, tittle: String){
        allocateActivityTittle(tittle)
        updateNavUser(user)
    }

}
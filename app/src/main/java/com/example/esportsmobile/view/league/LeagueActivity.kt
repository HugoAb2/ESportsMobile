package com.example.esportsmobile.view.league

import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.esportsmobile.DrawerBaseActivity
import com.example.esportsmobile.databinding.ActivityLeagueBinding
import com.example.esportsmobile.logic.adapters.PageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LeagueActivity : DrawerBaseActivity() {
    
    private lateinit var binding : ActivityLeagueBinding

    private lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager2

    private lateinit var tittle : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeagueBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tittle = intent.getStringExtra("league_name") as String

        implementsNavDrawer(tittle)

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
        leagueTeamsFragment.receiveData(tittle)

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

    private fun implementsNavDrawer(tittle: String){
        allocateActivityTittle(tittle)
        updateNavUser()
    }

}
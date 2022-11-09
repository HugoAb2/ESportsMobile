package com.example.esportsmobile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.esportsmobile.databinding.ActivityLoginBinding
import com.example.esportsmobile.view.PageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager2

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        configTabLayout()

    }

    private fun configTabLayout(){
        val adapter = PageAdapter(this)
        viewPager.adapter = adapter

        adapter.addFragment(SingInFragment(), "Sing In")
        adapter.addFragment(SingUpFragment(), "Sing Up")

        viewPager.offscreenPageLimit = adapter.itemCount
        val mediator = TabLayoutMediator(
            tabLayout, viewPager){
            tab : TabLayout.Tab, position : Int ->
            tab.text = adapter.getTitle(position)
        }
        mediator.attach()
    }
}
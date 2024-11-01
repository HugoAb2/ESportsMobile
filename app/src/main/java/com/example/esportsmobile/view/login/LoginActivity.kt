package com.example.esportsmobile.view.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.esportsmobile.dao.UsersDataSource
import com.example.esportsmobile.databinding.ActivityLoginBinding
import com.example.esportsmobile.model.User
import com.example.esportsmobile.adapters.PageAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class LoginActivity : AppCompatActivity() {

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager : ViewPager2

    private lateinit var binding : ActivityLoginBinding

    private lateinit var usersList : MutableList<User>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tabLayout = binding.tabLayout
        viewPager = binding.viewPager

        usersList = UsersDataSource.createUsersList()
        configTabLayout()

    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    private fun configTabLayout(){
        val adapter = PageAdapter(this)
        viewPager.adapter = adapter

        val singInFragment = SingInFragment()
        val singUpFragment = SingUpFragment()

        adapter.addFragment(singInFragment, "Sing In")
        adapter.addFragment(singUpFragment, "Sing Up")

        viewPager.offscreenPageLimit = adapter.itemCount
        val mediator = TabLayoutMediator(
            tabLayout, viewPager){
            tab : TabLayout.Tab, position : Int ->
            tab.text = adapter.getTitle(position)
        }
        mediator.attach()
    }
}
package com.example.esportsmobile

import android.content.Intent
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.example.esportsmobile.model.User
import com.google.android.material.navigation.NavigationView

open class DrawerBaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    private lateinit var drawerLayout : View
    private lateinit var navViewGlobal : NavigationView

    override fun setContentView(view: View?) {
        drawerLayout = layoutInflater.inflate(R.layout.activity_drawer_base, null)
        val container : FrameLayout = drawerLayout.findViewById(R.id.activityLayout)
        container.addView(view)
        super.setContentView(drawerLayout)

        val navView : NavigationView = drawerLayout.findViewById(R.id.nav_view)
        navViewGlobal = navView
        navView.setNavigationItemSelectedListener(this)

        val drawer : DrawerLayout = findViewById(R.id.drawerLayout)
        val toggle = ActionBarDrawerToggle(this, drawer, R.string.open, R.string.close)
        toggle.syncState()

        openMenuIcon(drawer)
    }


    private fun openMenuIcon(drawer : DrawerLayout){
        findViewById<ImageView>(R.id.menuIcon).setOnClickListener{
            drawer.open()
        }
    }

    protected fun allocateActivityTittle(activityTittle:String){
        val tittle = findViewById<TextView>(R.id.page_name_indicator)
        tittle.text = activityTittle
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.change_account -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            }
            R.id.log_out -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.update_profile ->{
                Toast.makeText(this, "Works", Toast.LENGTH_SHORT).show()
            }
        }
        return true
    }

    protected fun updateNavUser(user : User){
        val header = navViewGlobal.getHeaderView(0)

        if (user.profile == null){
            header.findViewById<ImageView>(R.id.user_profile).setImageResource((R.drawable.ic_baseline_person_24).hashCode())
        }else header.findViewById<ImageView>(R.id.user_profile).setImageResource(user.profile.hashCode())

        header.apply {
            findViewById<TextView>(R.id.user_name).text = user.name
            findViewById<TextView>(R.id.user_email).text = user.email
        }


    }
}
package com.example.esportsmobile

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.view.MenuItem
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.esportsmobile.view.EditProfileActivity
import com.example.esportsmobile.view.comment.CommentActivity
import com.example.esportsmobile.view.friend.FriendActivity
import com.example.esportsmobile.view.HomeActivity
import com.example.esportsmobile.view.MainActivity
import com.example.esportsmobile.view.login.LoginActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

open class DrawerBaseActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener{

    companion object{
        private const val TAG = "DrawerActivity"
    }

    private val userID = FirebaseAuth.getInstance().currentUser!!.uid
    private val db = FirebaseFirestore.getInstance()

    private lateinit var tittle : TextView
    private lateinit var drawerLayout : View
    private lateinit var navViewGlobal : NavigationView

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if(it.resultCode == Activity.RESULT_OK){
            Toast.makeText(this, "Changes applied", Toast.LENGTH_SHORT).show()
        }
        if (it.resultCode == Activity.RESULT_CANCELED){
            Toast.makeText(this, "Changes not applied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun setContentView(view: View?) {
        drawerLayout = layoutInflater.inflate(R.layout.activity_drawer_base, null)
        val container : FrameLayout = drawerLayout.findViewById(R.id.activityLayout)
        container.addView(view)
        super.setContentView(drawerLayout)

        val navView : NavigationView = drawerLayout.findViewById(R.id.nav_view)
        navViewGlobal = navView
        navView.setNavigationItemSelectedListener(this)
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNav)
        initBottomNav(bottomNav)

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

    private fun initBottomNav(bottomNav : BottomNavigationView) {
        bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    if (findViewById<TextView>(R.id.page_name_indicator).text != "Home"){
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                }
                R.id.comments -> {
                    if (findViewById<TextView>(R.id.page_name_indicator).text != "Comments"){
                        val intent = Intent(this, CommentActivity::class.java)
                        intent.putExtra("operation", "list")
                        startActivity(intent)
                        finish()
                    }
                }
                R.id.friends -> {
                    if (findViewById<TextView>(R.id.page_name_indicator).text != "Friends"){
                        val intent = Intent(this, FriendActivity::class.java)
                        intent.putExtra("operation", "list")
                        startActivity(intent)
                        finish()
                    }
                }
            }
            return@setOnItemSelectedListener true
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.change_account -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.log_out -> {
                FirebaseAuth.getInstance().signOut()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.update_profile ->{
                val intent = Intent(this, EditProfileActivity::class.java)
                intent.putExtra("userID", userID)
                launcher.launch(intent)
            }
            R.id.place ->{
                requestPermission()
                if (ActivityCompat.checkSelfPermission(this,
                        Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){

                    val intent = Intent(this, MapsActivity::class.java)
                    startActivity(intent)
                }

            }
        }
        return true
    }

    private fun requestPermission() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100
            )
        }
    }

    protected fun initNavUser(){
        val header = navViewGlobal.getHeaderView(0)
        db.collection("Users").document(userID).
                addSnapshotListener{user, _ ->
                    if (user != null){
                        header.apply {
                            findViewById<TextView>(R.id.user_name).text = user.getString("name")
                            findViewById<TextView>(R.id.user_email).text = user.getString("email")
                        }
                    }
                }
        FirebaseStorage.getInstance().getReference("profiles/").child(userID).downloadUrl.addOnSuccessListener {
            Picasso.get().load(it).into(header.findViewById<ImageView>(R.id.user_profile))
        }
    }

    protected fun allocateActivityTittle(activityTittle:String){
        tittle = findViewById(R.id.page_name_indicator)
        tittle.text = activityTittle
    }

}
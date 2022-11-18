package com.example.esportsmobile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.esportsmobile.dao.UsersDataSource
import com.example.esportsmobile.databinding.ActivityEditProfileBinding
import com.example.esportsmobile.model.User

class EditProfileActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "editActivity"
    }

    private lateinit var binding : ActivityEditProfileBinding
    private lateinit var updatedUser : User

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val userIntent = intent.getStringExtra("user_email") as String
        Log.d(TAG, "onCreate: $userIntent")
        updatedUser = receiveUserData(userIntent)
        Log.d(TAG, "onCreate: $updatedUser")
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            updateProfile.setOnClickListener {
                activityResponse(updateUser())
                finish()
            }
            cancelButton.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
            backIcon.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
        }
    }

    private fun activityResponse(user : User){
        val update = Intent()
        update.putExtra("user", user)
        Log.d(TAG, "activityResponse: $user")
        setResult(RESULT_OK, update)
    }

    private fun updateUser(): User{
        updatedUser.apply {
            name = binding.nameTxt.text.toString()
            profile = binding.profilePic.hashCode()
            age = binding.age.text.toString().toInt()
            country = binding.countryTxt.text.toString()
            email = binding.emailTxt.text.toString()
            password = binding.passwordTxt.text.toString()
        }
        return updatedUser
    }

    private fun receiveUserData(email : String): User {
        val user = UsersDataSource.createUsersList().find { it.email == email }
        binding.apply {
            if (user != null) {
                userId.text = user.id
                nameTxt.setText(user.name)
                age.setText(user.age.toString())
                countryTxt.setText(user.country)
                emailTxt.setText(user.email)
                passwordTxt.setText(user.password)

                if(user.profile != null) {
                    profilePic.setImageResource(user.profile!!)
                }
            }
            else{
                Log.d(TAG, "getUserData: User not found")
                setResult(RESULT_CANCELED)
            }
        }
        return user as User
    }

}
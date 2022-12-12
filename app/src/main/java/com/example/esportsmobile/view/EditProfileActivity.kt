package com.example.esportsmobile.view

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.example.esportsmobile.databinding.ActivityEditProfileBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso

class EditProfileActivity : AppCompatActivity() {

    companion object{
        private const val GALLERY_PERMISSION = android.Manifest.permission.READ_EXTERNAL_STORAGE
    }

    private lateinit var binding : ActivityEditProfileBinding
    private lateinit var userID : String
    private lateinit var userDoc : DocumentReference
    private lateinit var fAuth: FirebaseAuth
    private lateinit var storage : FirebaseStorage

    private lateinit var dialog: AlertDialog
    private lateinit var mImageURI : Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userID = intent.getStringExtra("userID") as String
        userDoc = FirebaseFirestore.getInstance().collection("Users").document(userID)
        fAuth = FirebaseAuth.getInstance()
        storage = FirebaseStorage.getInstance()

        receiveUserData()

    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            profilePic.setOnClickListener {
                checkPermissionGallery()
            }
            updateProfile.setOnClickListener {
                updateUser()
                setResult(RESULT_OK)
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


    private fun updateUser(){
        userDoc.apply {
            update("name", binding.nameTxt.text.toString())
            update("age", binding.age.text.toString())
            update("country", binding.countryTxt.text.toString())
            update("password", binding.passwordTxt.text.toString())
        }
        fAuth.currentUser?.updatePassword(binding.passwordTxt.text.toString())
        if (storage.getReference("profiles/$userID").name == "" ){
            storage.getReference("profiles/$userID").putFile(mImageURI)
        }else{
            storage.getReference("profiles/$userID").delete()
            storage.getReference("profiles/$userID").putFile(mImageURI)
        }
    }

        private fun receiveUserData() {

            storage.getReference("profiles/").child(userID).downloadUrl.addOnSuccessListener {
                Picasso.get().load(it).into(binding.profilePic)
            }

            userDoc.addSnapshotListener{user, _ ->

                binding.apply {
                    if (user != null) {
                        userId.text = userID
                        nameTxt.setText(user.getString("name"))
                        age.setText(user.getString("age"))
                        countryTxt.setText(user.getString("country"))
                        emailTxt.text = user.getString("email")
                        passwordTxt.setText(user.getString("password"))
                    }
                }
            }
    }

    private val requestGallery =
        registerForActivityResult(ActivityResultContracts.RequestPermission()){ permission ->
            if (permission){
                resultGallery.launch(
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))
            }else{
                showDialogPermission()
            }
        }

    private val resultGallery =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){ result ->
            if(result.data?.data != null) {
                mImageURI = result.data?.data as Uri
                binding.profilePic.setImageURI(mImageURI)
            }
        }


    private fun checkPermissionGallery(){
        val granted = checkPermission(GALLERY_PERMISSION)

        when {
            granted ->  resultGallery.launch(
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI))

            shouldShowRequestPermissionRationale(GALLERY_PERMISSION) -> showDialogPermission()

            else -> requestGallery.launch(GALLERY_PERMISSION)
        }
    }

    private fun showDialogPermission(){
        val builder = AlertDialog.Builder(this)
            .setTitle("Request").setMessage("we need permission to access this smartphone gallery")
            .setNegativeButton("decline") { _,_ -> dialog.dismiss()}
            .setPositiveButton("accept") { _,_ ->
                val intent = Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                    Uri.fromParts("package", packageName, null)
                )
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                dialog.dismiss()
            }
    }

    private fun checkPermission(permission : String) =
        ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED


}
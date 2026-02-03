package com.example.exercice3xml

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import android.content.Intent
import android.net.Uri
import android.widget.ImageButton

class Profile: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_profile)

        val name = intent.getStringExtra("USER_NAME")
        val firstname = intent.getStringExtra("USER_FIRSTNAME")
        val age = intent.getStringExtra("USER_AGE")
        val areaOfExpertise = intent.getStringExtra("USER_EXPERTISE")
        val phoneNumber = intent.getStringExtra("USER_PHONE")

        val nameTextView = findViewById<TextView>(R.id.profile_name)
        val firstnameTextView = findViewById<TextView>(R.id.profile_firstname)
        val ageTextView = findViewById<TextView>(R.id.profile_age)
        val expertiseTextView = findViewById<TextView>(R.id.profile_expertise)
        val phoneTextView = findViewById<TextView>(R.id.profile_phone_number)

        nameTextView.text = name
        firstnameTextView.text = firstname
        ageTextView.text = age
        expertiseTextView.text = areaOfExpertise
        phoneTextView.text = phoneNumber

        val callButton = findViewById<ImageButton>(R.id.call_button)

        callButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:$phoneNumber")
            startActivity(intent)
        }


        val editInfosButton = findViewById<Button>(R.id.profile_edit_infos_btn)
        editInfosButton.setOnClickListener {
            val intent = Intent(this, Main::class.java)
            startActivity(intent)
        }
    }
}
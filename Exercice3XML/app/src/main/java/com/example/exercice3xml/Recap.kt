package com.example.exercice3xml

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Button


class Recap: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_recap)

        val name = intent.getStringExtra("USER_NAME")
        val firstname = intent.getStringExtra("USER_FIRSTNAME")
        val age = intent.getStringExtra("USER_AGE")
        val areaOfExpertise = intent.getStringExtra("USER_EXPERTISE")
        val phoneNumber = intent.getStringExtra("USER_PHONE")

        val nameTextView = findViewById<TextView>(R.id.recap_name)
        val firstnameTextView = findViewById<TextView>(R.id.recap_firstname)
        val ageTextView = findViewById<TextView>(R.id.recap_age)
        val expertiseTextView = findViewById<TextView>(R.id.recap_area_of_expertise)
        val phoneTextView = findViewById<TextView>(R.id.recap_phone_number)

        nameTextView.text = name
        firstnameTextView.text = firstname
        ageTextView.text = age
        expertiseTextView.text = areaOfExpertise
        phoneTextView.text = phoneNumber

        val okButton = findViewById<Button>(R.id.ok_btn)
        okButton.setOnClickListener {
            val intent = Intent(this, Profile::class.java)

            intent.putExtra("USER_NAME", name)
            intent.putExtra("USER_FIRSTNAME", firstname)
            intent.putExtra("USER_AGE", age)
            intent.putExtra("USER_EXPERTISE", areaOfExpertise)
            intent.putExtra("USER_PHONE", phoneNumber)

            startActivity(intent)
        }

        val backButton = findViewById<Button>(R.id.recap_back_btn)
        backButton.setOnClickListener {
            finish()
        }
    }
}
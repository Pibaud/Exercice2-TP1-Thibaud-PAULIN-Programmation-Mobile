package com.example.exercice3xml

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class Main: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_exercice3_xml)

        /*
        Version Kotlin :

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.gravity = View.TEXT_ALIGNMENT_CENTER
        layout.setPadding(10, 10, 10, 10)

        val etLastName = EditText(this)
        // utiliser @strings pour les string
        etLastName.hint = getString(R.string.name)
        etLastName.inputType = InputType.TYPE_CLASS_TEXT

        val etFirstName = EditText(this)
        etFirstName.hint = getString(R.string.firstname)
        etFirstName.inputType = InputType.TYPE_CLASS_TEXT

        val etAge = EditText(this)
        etAge.hint = getString(R.string.age)
        etAge.inputType = InputType.TYPE_CLASS_NUMBER

        val etAreaOfExpertise = EditText(this)
        etAreaOfExpertise.hint = getString(R.string.area_of_expertise)
        etAreaOfExpertise.inputType = InputType.TYPE_CLASS_TEXT

        val etPhoneNumber = EditText(this)
        etPhoneNumber.hint = getString(R.string.phone_number)
        etPhoneNumber.inputType = InputType.TYPE_CLASS_PHONE

        val btnValidate = Button(this)
        btnValidate.text = getString(R.string.validate)

        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        layout.addView(etLastName, params)
        layout.addView(etFirstName, params)
        layout.addView(etAge, params)
        layout.addView(etAreaOfExpertise, params)
        layout.addView(etPhoneNumber, params)
        layout.addView(btnValidate, params)

        setContentView(layout)
         */

        val btnSubmit = findViewById<Button>(R.id.submit_btn)

        btnSubmit.setOnClickListener {
            val name = findViewById<EditText>(R.id.edit_text_name)
            val firstname = findViewById<EditText>(R.id.edit_text_firstname)
            val age = findViewById<EditText>(R.id.edit_text_age)
            val areaOfExpertise = findViewById<EditText>(R.id.edit_text_area_of_expertise)
            val phoneNumber = findViewById<EditText>(R.id.edit_text_phone_number)

            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.confirm_dialog_title))

            builder.setPositiveButton(getString(R.string.validate_dialog)) { dialog, _ ->
                val intent = Intent(this, Recap::class.java)

                intent.putExtra("USER_NAME", name.text.toString())
                intent.putExtra("USER_FIRSTNAME", firstname.text.toString())
                intent.putExtra("USER_AGE", age.text.toString())
                intent.putExtra("USER_EXPERTISE", areaOfExpertise.text.toString())
                intent.putExtra("USER_PHONE", phoneNumber.text.toString())

                startActivity(intent)
            }

            builder.setNegativeButton(getString(R.string.dismiss_dialog)) { dialog, _ ->
                dialog.dismiss()
            }

            val dialog: AlertDialog = builder.create()
            dialog.show()
        }
    }
}
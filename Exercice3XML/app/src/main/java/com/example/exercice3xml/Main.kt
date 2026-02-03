package com.example.exercice3xml

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.ImageView
import android.widget.TextView


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

        val domains = arrayOf("Ressources Humaines", "Design", "Marketing", "Physique", "Big Data / IA")
        val domainAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, domains)
        domainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        val domainSpinner = findViewById<Spinner>(R.id.spinner_expertise)
        domainSpinner.adapter = domainAdapter

        val countries = listOf(
            CountryItem("France", "+33", R.drawable.france_flag),
            CountryItem("Belgique", "+32", R.drawable.belgium_flag),
            CountryItem("Suisse", "+41", R.drawable.switzerland_flag)
        )

        val adapter = object : ArrayAdapter<CountryItem>(this, R.layout.spinner_item, countries) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                return createViewFromResource(position, convertView, parent)
            }

            override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
                return createViewFromResource(position, convertView, parent)
            }

            private fun createViewFromResource(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = convertView ?: layoutInflater.inflate(R.layout.spinner_item, parent, false)
                val item = getItem(position)

                view.findViewById<ImageView>(R.id.ivFlag).setImageResource(item!!.flag)
                view.findViewById<TextView>(R.id.tvCode).text = item.code

                return view
            }
        }

        val countryCodeSpinner = findViewById<Spinner>(R.id.country_phone_code)
        countryCodeSpinner.adapter = adapter

        val btnSubmit = findViewById<Button>(R.id.submit_btn)

        btnSubmit.setOnClickListener {
            // Récupération des vues
            val etName = findViewById<EditText>(R.id.edit_text_name)
            val etFirstname = findViewById<EditText>(R.id.edit_text_firstname)
            val etAge = findViewById<EditText>(R.id.edit_text_age)
            val etPhone = findViewById<EditText>(R.id.edit_text_phone_number)
            val tvError = findViewById<TextView>(R.id.tv_error_message)

            val fields = listOf(etName, etFirstname, etAge, etPhone)
            var allFilled = true

            for (field in fields) {
                if (field.text.toString().trim().isEmpty()) {
                    field.setBackgroundResource(R.drawable.bg_edittext_error)
                    allFilled = false
                } else {
                    field.setBackgroundResource(R.drawable.bg_edittext_success)
                }
            }

            if (!allFilled) {
                tvError.visibility = View.VISIBLE
            } else {
                tvError.visibility = View.GONE

                // Si tout est bon, on affiche la boîte de dialogue habituelle
                val areaOfExpertise = domainSpinner.selectedItem.toString()
                val selectedCountry = countryCodeSpinner.selectedItem as CountryItem
                val fullPhoneNumber = selectedCountry.code + etPhone.text.toString()

                val builder = AlertDialog.Builder(this)
                builder.setTitle(getString(R.string.confirm_dialog_title))
                builder.setPositiveButton(getString(R.string.validate_dialog)) { _, _ ->
                    val intent = Intent(this, Recap::class.java)
                    intent.putExtra("USER_NAME", etName.text.toString())
                    intent.putExtra("USER_FIRSTNAME", etFirstname.text.toString())
                    intent.putExtra("USER_AGE", etAge.text.toString())
                    intent.putExtra("USER_EXPERTISE", areaOfExpertise)
                    intent.putExtra("USER_PHONE", fullPhoneNumber)
                    startActivity(intent)
                }
                builder.setNegativeButton(getString(R.string.dismiss_dialog)) { d, _ -> d.dismiss() }
                builder.create().show()
            }
        }
    }
}
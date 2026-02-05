package com.example.trainapp.ui.view

import android.app.Activity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.trainapp.R
import com.example.trainapp.data.repository.TrainRepository
import com.example.trainapp.di.RetrofitClient
import com.example.trainapp.ui.viewmodel.TrainViewModel
import kotlinx.coroutines.launch

class Search: AppCompatActivity() { // activity ne suffisait pas pour utiliser les co routines lifecyclescope
    private val repository = TrainRepository(RetrofitClient.instance)
    private val viewModel = TrainViewModel(repository)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        val autoFrom = findViewById<AutoCompleteTextView>(R.id.auto_complete_from)
        val autoTo = findViewById<AutoCompleteTextView>(R.id.auto_complete_to)

        setupAutoComplete(autoFrom)
        setupAutoComplete(autoTo)
    }

    private fun setupAutoComplete(view: AutoCompleteTextView) {
        val adapter = ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, mutableListOf())
        view.setAdapter(adapter)

        view.addTextChangedListener { text ->
            viewModel.onQueryChanged(text.toString())
        }

        lifecycleScope.launch {
            viewModel.suggestions.collect { places ->
                val names = places.map { it.label }
                adapter.clear()
                adapter.addAll(names)
                adapter.notifyDataSetChanged()

                if (view.hasFocus() && names.isNotEmpty()) {
                    view.showDropDown()
                }
            }
        }
    }
}
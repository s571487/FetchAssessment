package com.example.fetchassessment

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val itemViewModel: ItemViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        itemViewModel.items.observe(this, Observer { items ->
            items?.let {
                populateSpinner(R.id.boxListId1, it[1], 1)
                populateSpinner(R.id.boxListId2, it[2], 2)
                populateSpinner(R.id.boxListId3, it[3], 3)
                populateSpinner(R.id.boxListId4, it[4], 4)
            }
        })
    }

    private fun populateSpinner(boxId: Int, items: List<Item>?, listId: Int) {
        val boxView = findViewById<View>(boxId)
        val spinner = boxView.findViewById<Spinner>(R.id.itemSpinner)
        val listIdTextView = boxView.findViewById<TextView>(R.id.listIdTextView)

        listIdTextView.text = "List ID: $listId"

        val itemNames = items?.map { it.name } ?: emptyList()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, itemNames)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }
}

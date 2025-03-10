package com.example.fragmentlifecycle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction


class MainActivity : AppCompatActivity() {
    private var currentFragmentId = 0 // Tracks the ID of the last added fragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addFragmentButton = findViewById<Button>(R.id.addFragmentButton)
        val changeViewButton = findViewById<Button>(R.id.changeViewButton)
        val numberInput = findViewById<EditText>(R.id.numberInput)

        addFragmentButton.setOnClickListener {
            currentFragmentId++
            addNewFragment(currentFragmentId)
            Log.d("MainActivity", "Fragment added with ID: $currentFragmentId")
        }

        changeViewButton.setOnClickListener {
            val enteredFragmentId = numberInput.text.toString().toIntOrNull()
            if (enteredFragmentId != null && enteredFragmentId > 0 && enteredFragmentId <= currentFragmentId) {
                showFragment(enteredFragmentId)
                Log.d("MainActivity", "Switched to Fragment ID: $enteredFragmentId")
            } else {
                Log.e("MainActivity", "Invalid input. Please enter a valid number within range.")
            }
        }
    }

    private fun addNewFragment(fragmentId: Int) {
        val fragment = Fragment1()
        val bundle = Bundle()
        bundle.putString("count", fragmentId.toString())
        fragment.arguments = bundle

        val fragmentManager: FragmentManager = supportFragmentManager
        val transaction: FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerView, fragment, "Fragment$fragmentId")
        transaction.addToBackStack("Fragment$fragmentId") // Add to back stack
        transaction.commit()
    }

    private fun showFragment(fragmentId: Int) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragment = fragmentManager.findFragmentByTag("Fragment$fragmentId")

        if (fragment != null) {
            // If fragment exists in the back stack, show it
            fragmentManager.popBackStack("Fragment$fragmentId", 0)
        } else {
            Log.e("MainActivity", "Fragment with ID $fragmentId not found in back stack.")
        }
    }
}
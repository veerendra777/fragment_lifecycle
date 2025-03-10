package com.example.fragmentlifecycle

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment

class Fragment1 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val count = arguments?.getString("count") ?: "0"

        val view = inflater.inflate(R.layout.fragment_1, container, false)

        val textView = view.findViewById<TextView>(R.id.fragTextView)
        textView.text = "Fragment Count: $count"

        Log.d("Fragment1", "Fragment created with count: $count")

        return view
    }
}
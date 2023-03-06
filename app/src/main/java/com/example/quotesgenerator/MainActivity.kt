package com.example.quotesgenerator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley

class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var btn = findViewById<Button>(R.id.button)
        btn.setOnClickListener(View.OnClickListener {
            loadQuote()
        })
        loadQuote()
    }

    fun loadQuote(){
        var tvQuote = findViewById<TextView>(R.id.tvQuote)
        var tvAuthor = findViewById<TextView>(R.id.tvAuthor)
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.quotable.io/random"

        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                val quote = response.getString("content")
                val author = response.getString("author")
                tvQuote.text = quote.toString()
                tvAuthor.text = "- " + author.toString()
            },
            { error ->
                tvQuote.text = "Check internet connection"
                tvAuthor.text = "the app lol"
            }
        )


// Access the RequestQueue through your singleton class.
        queue.add(jsonObjectRequest)
    }

}
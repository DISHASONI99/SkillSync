package com.example.splash_screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.ArrayList
import java.util.Locale

class skills_homepage : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.skills_homepage)

        recyclerView = findViewById(R.id.recyclerView)
        searchView = findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        addDataToList()
        var adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter


        adapter.setonItemClickListner(object : LanguageAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {
//                Toast.makeText(this@skills_homepage, "Welcome", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@skills_homepage, splash_screen::class.java)
                startActivity(intent)
            }

        })

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })
    }

    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(this, "No Data found", Toast.LENGTH_SHORT).show()
            }
            else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(
            LanguageData(
                "Singing",
                R.drawable.music,)

        )
        mList.add(
            LanguageData(
                "Guitar",
                R.drawable.guitar,
          )
        )
        mList.add(
            LanguageData(
                "piano",
                R.drawable.piano,
            )
        )
        mList.add(
            LanguageData(
                "flute",
                R.drawable.flute,
             )
        )
        mList.add(
            LanguageData(
                "dance",
                R.drawable.dance,
             )
        )
        mList.add(
            LanguageData(
                "Photography",
                R.drawable.photo,
            )
        )
        mList.add(
            LanguageData(
                "Yoga and Meditation",
                R.drawable.yoga,
            )
        )
        mList.add(
            LanguageData(
                "Writing",
                R.drawable.poet,
            )
        )
    }
}

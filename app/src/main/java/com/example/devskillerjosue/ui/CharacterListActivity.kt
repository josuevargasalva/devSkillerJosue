package com.example.devskillerjosue.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.devskillerjosue.R
import com.example.devskillerjosue.common.State
import com.example.devskillerjosue.ui.adapters.CharacterAdapter
import com.example.devskillerjosue.viewmodel.CharacterViewModel


class CharacterListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_list)

        //init all start variables
        var characterViewModel = CharacterViewModel();
        var list: RecyclerView = findViewById(R.id.CharacterListRecyclerView)
        var adapter:CharacterAdapter = CharacterAdapter(this);

        list.adapter = adapter
        list.layoutManager = LinearLayoutManager(this)
        adapter.onItemClick = { item ->
            val intent = Intent(this, CharacterDetailActivity::class.java)
            intent.putExtra("extra",item)
            startActivity(intent)
        }

        //get data and observe the status
        characterViewModel.getData();
        characterViewModel.state.observe(this) {
            when (it) {
                State.ERROR -> {
                    showProgress(View.INVISIBLE)
                    Toast.makeText(
                        this, "Error in the service, sorry",
                        Toast.LENGTH_LONG
                    ).show()
                }

                State.LOADING -> {
                    showProgress(View.VISIBLE)
                }

                State.SUCCESS -> {
                    showProgress(View.INVISIBLE)
                    adapter.list = characterViewModel.currentData.value!!
                    adapter.notifyDataSetChanged()
                }

                else -> {
                    showProgress(View.INVISIBLE)
                }
            }
        }
    }

    //show and hide the progress bar
    fun showProgress(visibility: Int){
        findViewById<ProgressBar>(R.id.progressBar).visibility = visibility
    }

}
package com.example.devskillerjosue.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.devskillerjosue.R
import com.example.devskillerjosue.models.MCharacter
import com.squareup.picasso.Picasso

class CharacterDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_detail)

        //get item from other screen
        var character:MCharacter = intent.getSerializableExtra("extra") as MCharacter

        // load data in detail page
        findViewById<TextView>(R.id.titleTextView).setText(character.name)
        findViewById<TextView>(R.id.subTitleTextView).setText(character.species)

        var image = findViewById<ImageView>(R.id.imageView)

        Picasso.get()
            .load(character.image)
            .into(image);
    }
}
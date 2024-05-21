package com.example.devskillerjosue.ui.adapters

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.devskillerjosue.R
import com.example.devskillerjosue.common.BaseAdapter
import com.example.devskillerjosue.models.MCharacter
import com.squareup.picasso.Picasso

class CharacterAdapter (private val context: Context) : BaseAdapter<MCharacter, CharacterAdapter.ListHolder>() {

    // Attributes.
    override var itemLayout = R.layout.card_character

    /**
     * Sets the ViewHolder for the item.
     * @param view
     * @return View ListHolder(view)
     */
    override fun instantiateViewHolder(view: View): RecyclerView.ViewHolder = ListHolder(view)

    /**
     * Defines the cell values and actions.
     * Loads the data in the custom adapter.
     * @param itemView
     */
    inner class ListHolder(itemView: View?) : BaseViewHolder<MCharacter>(itemView) {

        /**
         * Links the item list to the custom cell
         * @param item
         */
        @Suppress("SENSELESS_COMPARISON")
        override fun showItem(item: MCharacter) {
            itemView.findViewById<TextView>(R.id.titleTextView).setText(item.name)
            itemView.findViewById<TextView>(R.id.subTitleTextView).setText(item.species)



            var image = itemView.findViewById<ImageView>(R.id.imageView)

            Picasso.get()
                .load(item.image)
                .into(image);


            itemView.findViewById<ConstraintLayout>(R.id.cardConstraintLayout).setOnClickListener {
                onItemClick!!.invoke(item)
            }

        }

    }
}
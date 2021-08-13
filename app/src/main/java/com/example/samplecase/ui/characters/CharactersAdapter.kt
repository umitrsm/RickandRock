package com.example.samplecase.ui.characters

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.samplecase.Api.model.Result
import com.example.samplecase.R
import com.example.samplecase.ui.characterDetail.CharactersDetailActivity
import java.util.ArrayList

class CharactersAdapter(val charactersList: List<Result>) : RecyclerView.Adapter<CharactersAdapter.ModelViewHolder>() {

    class ModelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val characterName: TextView = view.findViewById(R.id.tv_character_name)
        val characterImage: ImageView = view.findViewById(R.id.iv_characters)
        val adapterView: View = view

        fun bindItems(item: Result) {
           characterName.text = item.name!!
            Glide.with(characterImage.context)
                .load(item.image!!)
                .timeout(15000)
                .apply {
                    RequestOptions()
                        .error(R.drawable.ic_launcher_background)
                }
                .into(characterImage)
            characterImage.setOnClickListener{
                Log.d("TAG", "bindItems: ${item.episode}" )

                val intent =  Intent(adapterView.context, CharactersDetailActivity::class.java)
                val bundle = Bundle()
                bundle.putStringArrayList("episodes", item.episode as ArrayList<String>?)
                bundle.putString("name", item.name)
                bundle.putString("status",item.status)
                bundle.putString("gender",item.gender)
                bundle.putString("species",item.species)
                bundle.putString("imageUrl",item.image)
                intent.putExtras(bundle)
                adapterView.context.startActivity(intent)

            }
        }




    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_character, parent, false)

        return ModelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return charactersList.size
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {
        holder.bindItems(charactersList.get(position))
    }
}
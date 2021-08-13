package com.example.samplecase.ui.characterDetail

import android.os.Bundle
import android.widget.Button
import android.widget.ExpandableListView
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.samplecase.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class CharactersDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters_detail)

        val name = intent.getStringExtra("name")
        val episodes: ArrayList<String>? = intent.getStringArrayListExtra("episodes")
        val characterImage: ImageView = findViewById(R.id.iv_character_detail)
        val image = intent.getStringExtra("imageUrl")
        val back_button:Button = findViewById(R.id.cancel_button)

        back_button.setOnClickListener{
            val count = supportFragmentManager.backStackEntryCount

            if (count == 0) {
                super.onBackPressed()
                //additional code
            } else {
                supportFragmentManager.popBackStack()
            }
        }

        val expandedEpisodeList: ExpandableListView = findViewById(R.id.expandableListView)

        expandedEpisodeList.setAdapter(episodes?.let { EpisodesAdapter(this, it) })

        val characterInfo = intent.getStringExtra("status") + ", " + intent.getStringExtra("species") + "\n\n" + name
        val tv_characterInfo: TextView = findViewById(R.id.characterInfo)
        tv_characterInfo.text = characterInfo


        Glide.with(this)
            .load(image)
            .timeout(15000)
            .apply {
                RequestOptions()
                    .error(R.drawable.ic_launcher_background)
            }
            .into(characterImage)
    }
}
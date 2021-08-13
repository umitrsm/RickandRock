package com.example.samplecase.ui.characters

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.samplecase.R
import com.example.samplecase.datasource.Status
import com.github.ajalt.timberkt.Timber
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_characters.*
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
@ExperimentalCoroutinesApi
class CharactersActivity : AppCompatActivity() {

    private val viewModel : CharactersViewModel by viewModels()

    lateinit var rvCharacter : RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_characters)

        rvCharacter = findViewById(R.id.rv_characters)
        val progressBar : LinearLayout = findViewById(R.id.ll_progress_item)
        viewModel.getCharacters().observe(this, {
            when(it.status) {
                Status.SUCCESS->{
                    progressBar.visibility = View.GONE
                    Timber.d { "$it" }
                    rvCharacter.adapter = CharactersAdapter(charactersList = it.data!!.results!!)
                }
            }
        })
    }
}
package com.example.samplecase.ui.characters

import androidx.lifecycle.ViewModel

import com.example.samplecase.repository.CharactersRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.samplecase.Api.model.BaseCharacters
import com.example.samplecase.datasource.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect

@ExperimentalCoroutinesApi
@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val charactersRepository: CharactersRepository
) : ViewModel() {

    private val characters: MutableLiveData<Resource<BaseCharacters>> = MutableLiveData()

    fun getCharacters(): LiveData<Resource<BaseCharacters>> {
        return if (characters.value?.data == null) {
            viewModelScope.launch {
                charactersRepository.fetchCharacters().collect{
                    characters.postValue(it)
                }
            }
            characters
        } else {
            characters
        }
    }
}
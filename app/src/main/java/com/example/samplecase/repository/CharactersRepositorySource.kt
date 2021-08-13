package com.example.samplecase.repository

import com.example.samplecase.Api.model.BaseCharacters
import com.example.samplecase.datasource.Resource
import kotlinx.coroutines.flow.Flow

interface CharactersRepositorySource {

    suspend fun fetchCharacters(): Flow<Resource<BaseCharacters>>
}
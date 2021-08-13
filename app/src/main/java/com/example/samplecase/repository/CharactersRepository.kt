package com.example.samplecase.repository

import android.util.Log
import com.example.samplecase.Api.RickAndMortApi
import com.example.samplecase.Api.model.BaseCharacters
import com.example.samplecase.datasource.Resource
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*

@ExperimentalCoroutinesApi
class CharactersRepository @Inject constructor(
    private val rickAndMortApi: RickAndMortApi
): CharactersRepositorySource {

    override suspend fun fetchCharacters(): Flow<Resource<BaseCharacters>> {
        return flow {
            emit(Resource.loading(null))
            val characters = rickAndMortApi.getCharacters()
            emit(Resource.success(characters))
        }.retryWhen { cause, attempt ->
            Log.d("TAG", "attempt count -> $attempt")
            Log.e("TAG", "cause -> $cause")
            (cause is Exception).also { if (it) delay(10_000) }
        }.catch {
            emit(Resource.error(it.localizedMessage, null, it))
        }.flowOn(Dispatchers.IO)

    }


}
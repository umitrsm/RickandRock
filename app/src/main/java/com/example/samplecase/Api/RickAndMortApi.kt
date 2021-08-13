package com.example.samplecase.Api

import com.example.samplecase.Api.model.BaseCharacters
import retrofit2.http.GET

interface RickAndMortApi {

    @GET("character")
    suspend fun getCharacters(): BaseCharacters


 /*   @GET("location")
    suspend fun getLocations(

    ): RickAndMortApi

    @GET("episode")
    suspend fun getEpisodes(

    ): RickAndMortEpisodes */
}
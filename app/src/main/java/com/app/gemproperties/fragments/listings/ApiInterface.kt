package com.app.gemproperties.fragments.listings

import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET("/api/task-list/") // maybe remove the final dash
    suspend fun getHouse(): Response<List<House>>


@POST("/api/task-create/")
suspend fun createTask(@Body house: House): Response<House>

//    @GET("/9Kfqfp") // maybe remove the final dash
//    suspend fun getHousetwo(): List<House>


}
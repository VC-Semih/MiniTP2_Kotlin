package com.example.myapplication3.model

sealed class MyObjectForRecyclerView

data class ObjectDataSample(
    val cars: String,
    val maxSpeed: Float
) : MyObjectForRecyclerView()

data class ObjectDataHeaderSample(
    val headerText: String,
) : MyObjectForRecyclerView()

data class ObjectDataFooterSample(
    val footerText: String,
) : MyObjectForRecyclerView()
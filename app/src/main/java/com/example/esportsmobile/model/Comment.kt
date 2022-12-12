package com.example.esportsmobile.model

import java.io.Serializable

data class Comment(
    val id : String,
    var author : String,
    var target : String,
    var text : String
): Serializable

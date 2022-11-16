package com.example.esportsmobile.model

data class League(
    var id : String,
    var name : String,
    var region : String,
    var season : String,
    var shortestGame : String,
    var averageGameTime : String,
    var longestGame : String,
    var averageGameKills : String,
    var icon : Int
)


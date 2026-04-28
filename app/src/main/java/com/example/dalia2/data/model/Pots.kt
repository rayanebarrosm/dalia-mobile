package com.example.dalia2.data.model

import java.time.Instant

data class Posts(
    val idPost:String,
    val idUser: String,
    val titulo:String,
    val content:String,
    val likesValue: Int,
    val createdAt: Instant,
    val comments: List<Comments>
)

data class Comments(
    val idComment:String,
    val idUser: String,
    val comment:String,
    val createdAt: Instant
)

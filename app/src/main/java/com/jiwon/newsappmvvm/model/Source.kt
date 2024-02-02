package com.jiwon.newsappmvvm.model

import androidx.room.Entity

@Entity
data class Source(
    val id: String,
    val name: String
)
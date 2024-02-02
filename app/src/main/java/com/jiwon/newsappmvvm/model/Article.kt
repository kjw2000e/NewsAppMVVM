package com.jiwon.newsappmvvm.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "articles")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null, // breaking, search 에서는 안쓰기 때문에 nullabale 해야됨
    val author: String? = null,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source, // room 은 only primitive data types 만을 다룰 수 있다. type converter를 만들어야함
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable
{
    override fun hashCode(): Int {
        var result = id.hashCode()
        if (url.isNullOrEmpty()) {
            result = 31 * result + url.hashCode()
        }
        return result
    }

    override fun toString(): String {
        return super.toString()
    }
}
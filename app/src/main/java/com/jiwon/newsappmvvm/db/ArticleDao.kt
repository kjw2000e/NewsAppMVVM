package com.jiwon.newsappmvvm.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jiwon.newsappmvvm.model.Article

@Dao
interface ArticleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE) // 이미 존재하는 경우 대체 한다
    suspend fun upsert(article: Article): Long // update + insert, id를 리턴함

    @Query("SELECT * FROM articles")
    fun getAllArticles(): LiveData<List<Article>> // fragment 에서 observe 할 수 있도록 LiveData 반환

    @Delete
    suspend fun deleteArticle(article: Article)
}
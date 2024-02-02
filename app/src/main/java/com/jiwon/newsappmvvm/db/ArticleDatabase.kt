package com.jiwon.newsappmvvm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jiwon.newsappmvvm.model.Article


// database class 는 abstract class 로 만들어야 하고
// dao를 프로퍼티로 갖는다.
// create singleton class

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

//    abstract val dao: ArticleDao // 이렇게 해도 되고 함수로 만들어도됨
    abstract fun getArticleDao(): ArticleDao // 구현 필요없음, room이 처리함

//    companion object {
//        // Singleton prevents multiple instances of database opening at the
//        // same time.
////      @Volatile : Java 변수를 메인 메모리에 저장하겠다는 것을 명시하기 위해 사용.
//        // 반드시 synchronized 사용해야함
//        @Volatile // 인스턴스를 변경하지 못하도록 인스턴스가 한번에 하나만 존재하도록 함
//        private var INSTANCE: ArticleDatabase? = null
//
//        fun getInstance(context: Context): ArticleDatabase {
//            synchronized(this) {
//                var instance = INSTANCE
//                if (instance == null) {
//                    instance = Room.databaseBuilder(context.applicationContext,
//                        ArticleDatabase::class.java,
//                        "article_database.db"
//                    )
//                        .fallbackToDestructiveMigration()
//                        .build()
//                    INSTANCE == instance
//                }
//                return instance
//            }
//        }
//    }

//    companion object {
//        @Volatile
//        private var instance: ArticleDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
//            instance ?: createDatabase(context).also { instance = it }
//        }
//
//        fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                ArticleDatabase::class.java,
//                "article_db.db"
//            ).build()
//    }
}
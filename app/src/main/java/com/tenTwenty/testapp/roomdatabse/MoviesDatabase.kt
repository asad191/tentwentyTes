package com.tenTwenty.testapp.roomdatabse

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results
import com.tenTwenty.testapp.ui.watch.localCache.UpComingDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Results::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun movieDao(): UpComingDao


    companion object {
        private var INSTANCE: MoviesDatabase? = null
        private const val DB_NAME = "movies.db"

        fun getDatabase(context: Context): MoviesDatabase {
            if (INSTANCE == null) {
                synchronized(MoviesDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext, MoviesDatabase::class.java, DB_NAME)
                            //.allowMainThreadQueries() // Uncomment if you don't want to use RxJava or coroutines just yet (blocks UI thread)
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)

//                                    GlobalScope.launch(Dispatchers.IO) { rePopulateDb(INSTANCE) }
                                }
                            }).build()
                    }
                }
            }

            return INSTANCE!!
        }
    }
}
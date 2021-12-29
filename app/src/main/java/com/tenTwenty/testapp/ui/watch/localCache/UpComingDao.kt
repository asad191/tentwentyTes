package com.tenTwenty.testapp.ui.watch.localCache

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tenTwenty.testapp.responseModel.upcommingMovieResponseModel.Results

@Dao
interface UpComingDao {

    @Query("SELECT * FROM UP_Coming_Movie WHERE id = :id LIMIT 1")
    suspend fun findMovieById(id: Int):Results?

    @Query("SELECT * FROM UP_Coming_Movie WHERE title = :fullName LIMIT 1")
    suspend fun findMovieByName(fullName: String?):Results?


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(movie:ArrayList<Results>)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    suspend fun update(movie: Results)

    @Query("DELETE FROM UP_Coming_Movie")
    suspend fun deleteAll()

    @get:Query("SELECT * FROM UP_Coming_Movie ORDER BY id ASC")
  public  val allMovie: LiveData<List<Results>>
}
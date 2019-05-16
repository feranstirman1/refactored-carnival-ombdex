package me.nelsoncastro.pokeapichingona.Database.Domain

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import me.nelsoncastro.pokeapichingona.Models.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: Movie)

    @Query("select * from Movie")
    fun loadAllMovies(): LiveData<List<Movie>>
}
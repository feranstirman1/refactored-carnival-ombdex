package me.nelsoncastro.pokeapichingona.Models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Movie (
        @PrimaryKey val id: Int = 0,
        val Title:String = "N/A",
        val Year:String = "N/A",
        val Released: String = "N/A",
        val Runtime:String = "N/A",
        val Genre:String = "N/A",
        val Director:String = "N/A",
        val Actors:String = "N/A",
        val Plot:String = "N/A",
        val Language:String = "N/A",
        val imdbRating:String = "N/A",
        val Poster:String = "N/A"
)

data class OmbdMovieResponse (
        val Search: List<Movie>,
        val totalResults: String,
        val Response: Boolean
)
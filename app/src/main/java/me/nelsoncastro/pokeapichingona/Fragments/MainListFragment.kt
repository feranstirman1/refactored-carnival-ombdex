package me.nelsoncastro.pokeapichingona.Fragments

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.movies_list_fragment.*
import kotlinx.android.synthetic.main.movies_list_fragment.view.*
import me.nelsoncastro.pokeapichingona.Adapters.RVMovieAdapter
import me.nelsoncastro.pokeapichingona.Constants.AppConstants
import me.nelsoncastro.pokeapichingona.Models.Movie
import me.nelsoncastro.pokeapichingona.ViewModel.MovieViewModel
import java.lang.RuntimeException
import androidx.appcompat.widget.SearchView
import me.nelsoncastro.pokeapichingona.R


class MainListFragment: Fragment() {

    private lateinit var moviesAdapter: RVMovieAdapter
    var listenerTool : ClickedMovieListener? = null

    interface ClickedMovieListener{
        fun managePortraitItemClick(movie: Movie)
        fun managedLandscapeItemClick(movie: Movie)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ClickedMovieListener) {
            listenerTool = context
        } else {
            throw RuntimeException("Se necesita una implementación de la interfaz")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listenerTool = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(me.nelsoncastro.pokeapichingona.R.layout.movies_list_fragment, container, false)


        val MovieViewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)

        //val resultSearch = MovieViewModel.getMovieByName(query).value
        //moviesAdapter.changeDataSet(resultSearch?: AppConstants.emptymovies)

        initRecyclerView(resources.configuration.orientation, view)

        MovieViewModel.getAll().observe(this, Observer { result ->
            moviesAdapter.changeDataSet(result)
        })

        return view
    }

    fun initRecyclerView(orientation: Int, container: View) {
        val linearLayoutManager = LinearLayoutManager(this.context)
        val recyclerview  = container.rv_list

        moviesAdapter = if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            RVMovieAdapter(movies = AppConstants.emptymovies, clickListener = { movie: Movie -> listenerTool?.managePortraitItemClick(movie)})
        }else {
            RVMovieAdapter(movies = AppConstants.emptymovies, clickListener = {movie: Movie -> listenerTool?.managedLandscapeItemClick(movie)})
        }

        recyclerview.apply {
            adapter = moviesAdapter
            setHasFixedSize(true)
            layoutManager = linearLayoutManager
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)

        val searchView = menu.findItem(R.id.app_bar_search).actionView as SearchView
        searchView.isSubmitButtonEnabled = true

        searchView.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

}

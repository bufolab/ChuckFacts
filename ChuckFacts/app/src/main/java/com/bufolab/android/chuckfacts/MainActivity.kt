package com.bufolab.android.chuckfacts

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.bufolab.android.chuckfacts.data.LocalRepository
import com.bufolab.android.chuckfacts.domain.model.ChuckFact
import com.bufolab.android.chuckfacts.presenter.MainPresenter
import com.bufolab.android.chuckfacts.view.ChuckFactAdapter
import com.bufolab.android.chuckfacts.view.DeckLayoutManager
import com.bufolab.android.chuckfacts.view.MainView
import com.bufolab.android.chuckfacts.view.helper.MyItemTouch
import javax.inject.Inject

/**
 * Created by Bufolab on 12/08/2018.
 */

class MainActivity : AppCompatActivity(), MainView {


    lateinit var stackFilmAdapter: ChuckFactAdapter

    @Inject
    lateinit var mainViewPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initialization()

        bindUI()
    }

    private fun bindUI() {
        val v = findViewById<RecyclerView>(R.id.recycler_view)
        v.layoutManager = DeckLayoutManager( true)

        stackFilmAdapter = ChuckFactAdapter(this)
        v.adapter = stackFilmAdapter

        val itemTouchHelper = ItemTouchHelper(MyItemTouch(mainViewPresenter, stackFilmAdapter))
        itemTouchHelper.attachToRecyclerView(v)
        stackFilmAdapter.notifyDataSetChanged()
    }


    //as we dont use dagger, we will initialize the Repository here. Should be in the Application.
    //but this is is just for fun.
    private fun initialization() {

        LocalRepository(applicationContext)

        //inject dependencies
        ChuckFactsApplication.component.inject(this)
        mainViewPresenter.setView(this)
    }

    override fun onResume() {
        super.onResume()
        mainViewPresenter.onInitialize()
    }

    override fun onStop() {
        super.onStop()
        mainViewPresenter.onFinish()
    }
    override fun showItems(list: List<ChuckFact>) {
        stackFilmAdapter.list =  arrayListOf(stackFilmAdapter.list,list).flatten()
    }

    override fun showLoading() {
        findViewById<ProgressBar>(R.id.loading).visibility = View.VISIBLE
    }

    override fun hideLoading() {
        findViewById<ProgressBar>(R.id.loading).visibility = View.GONE
    }

    override fun setAmountSavedJokes(amount: Int) {
        val textViewSavedJokes = findViewById<TextView>(R.id.saved_jokes)
        textViewSavedJokes.text = getString(R.string.you_have) + " $amount " + getString(R.string.facts_saved)
    }

    override fun setEmptySavedJokes() {
        val textViewSavedJokes = findViewById<TextView>(R.id.saved_jokes)
        textViewSavedJokes.text = getString(R.string.swipe_left_to_save_facts)
    }
}

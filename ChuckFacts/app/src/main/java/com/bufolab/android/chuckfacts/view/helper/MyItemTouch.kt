package com.bufolab.android.chuckfacts.view.helper

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import com.bufolab.android.chuckfacts.presenter.MainPresenter
import com.bufolab.android.chuckfacts.view.ChuckFactAdapter

/**
 * Created by Bufolab on 12/08/2018.
 */

class MyItemTouch(var presenter: MainPresenter, val adapter: ChuckFactAdapter) : ItemTouchHelper.Callback() {


    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val swipeFlags = ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        return ItemTouchHelper.Callback.makeMovementFlags(0, swipeFlags)
    }

    override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        val adapterPosition = viewHolder.adapterPosition

        when (direction) {
            ItemTouchHelper.LEFT -> {
                presenter.onItemRejected(adapter.getItemAt(adapterPosition))
                val toMutableList = adapter.list.toMutableList()
                toMutableList.removeAt(adapterPosition)
                adapter.list = toMutableList
            }
            ItemTouchHelper.RIGHT -> {
                presenter.onItemAccepted(adapter.getItemAt(adapterPosition))
                val toMutableList = adapter.list.toMutableList()
                toMutableList.removeAt(adapterPosition)
                adapter.list = toMutableList
            }
        }
    }

}
package com.bufolab.android.chuckfacts.view

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bufolab.android.chuckfacts.R
import com.bufolab.android.chuckfacts.domain.model.ChuckFact

/**
 * Created by Bufolab on 02/07/2018.
 */
class ChuckFactAdapter(context: Context) : RecyclerView.Adapter<ChuckFactAdapter.ChuckJokeHolder>() {

    var list: List<ChuckFact> = emptyList()
        set(newList) {
            var listener = object : DiffUtil.Callback() {

                val oldList = this@ChuckFactAdapter.list

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return oldList[oldItemPosition].id == newList[newItemPosition].id
                }

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return oldList[oldItemPosition].id == newList[newItemPosition].id
                }

                override fun getOldListSize(): Int {
                    return oldList.size
                }

                override fun getNewListSize(): Int {
                    return newList.size
                }

            }

            val calculateDiff = DiffUtil.calculateDiff(listener)
            calculateDiff.dispatchUpdatesTo(this)
            field = ArrayList(newList)
        }

    private var inflater: LayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater


    fun getItemAt(adapterPosition: Int): ChuckFact {
        return list[adapterPosition]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChuckJokeHolder {
        val view = inflater.inflate(R.layout.card_view, parent, false)
        return ChuckJokeHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ChuckJokeHolder, position: Int) {
        val joke = list[position]
        holder.quoteId = joke.id
        holder.value.text = joke.value
        holder.category.text = if(joke.category ==null) "" else joke.category.toString()
    }


    class ChuckJokeHolder(v: View) : RecyclerView.ViewHolder(v) {
        val view: View = v
        var quoteId: String = Long.MIN_VALUE.toString()
        val value: TextView = v.findViewById(R.id.value)
        val category: TextView = v.findViewById(R.id.category)
    }
}
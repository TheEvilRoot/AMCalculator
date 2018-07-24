package com.theevilroot.amcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

data class Result(val expression: String, val result: String)

class ResultViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val expr by lazy { itemView.findViewById<TextView>(R.id.result_expr) }
    private val res by lazy { itemView.findViewById<TextView>(R.id.result_res) }
    fun bind(result: Result) {
        expr.text = result.expression
        res.text = result.result
    }
}

class ResultViewAdapter: RecyclerView.Adapter<ResultViewHolder>() {

    var items: List<Result> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder =
            ResultViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.result_item_view, parent, false))

    override fun getItemCount(): Int =
            items.count()

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) =
        holder.bind(items[position])

}
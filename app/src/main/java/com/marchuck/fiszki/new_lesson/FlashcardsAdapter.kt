package com.marchuck.fiszki.new_lesson

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marchuck.fiszki.R
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Flashcard

class FlashcardsAdapter : RecyclerView.Adapter<FlashcardsAdapter.ViewHolder>() {
    interface Listener {
        fun onDelete(item: Flashcard, position: Int)

    }

    var listener: Listener? = null

    var items = arrayListOf<Flashcard>()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        return ViewHolder(
                LayoutInflater.from(p0.context).inflate(R.layout.flashcard_added_row, p0, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        p0.bind(items[p1], listener, p1)
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val bin = v.findViewById<View>(R.id.bin)
        val heads = v.findViewById<TextView>(R.id.heads)
        val tails = v.findViewById<TextView>(R.id.tails)

        fun bind(item: Flashcard, listener: Listener?, position: Int) {
            listener?.let {
                bin.setOnClickListener {
                    listener.onDelete(item, position)
                }
            }
            heads.text = item.heads
            tails.text = item.tails
        }
    }
}
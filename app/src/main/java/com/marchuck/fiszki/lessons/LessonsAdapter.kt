package com.marchuck.fiszki.lessons

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.marchuck.fiszki.R


typealias Listener = (str: String) -> Unit

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    var listener: Listener? = null

    var items: List<String> = arrayListOf ()

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): LessonViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item_lesson, p0, false)
        return LessonViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(p0: LessonViewHolder, p1: Int) {
        val item = items[p1]
        p0.bind(item)
        p0.itemView.setOnClickListener {
            listener?.invoke(item)
        }
    }

    class LessonViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        val textView by lazy { v.findViewById<TextView>(R.id.item_lesson_text) }

        fun bind(txt: String) {
            textView.text = txt
        }
    }
}
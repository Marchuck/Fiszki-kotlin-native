package com.marchuck.fiszki.lessons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.marchuck.fiszki.R
import org.kotlin.mpp.mobile.com.marchuck.fiszki.model.Lesson


typealias Listener = (lesson: Lesson) -> Unit

class LessonsAdapter : RecyclerView.Adapter<LessonsAdapter.LessonViewHolder>() {

    var listener: Listener? = null

    var items: List<Lesson> = arrayListOf()

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

        fun bind(lesson: Lesson) {
            textView.text = lesson.name
        }
    }
}
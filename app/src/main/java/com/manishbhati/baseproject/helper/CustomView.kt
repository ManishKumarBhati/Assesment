package com.manishbhati.baseproject.helper

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.manishbhati.baseproject.R

class CustomView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {
    var imageView: ImageView
    var textView: TextView

    init {
        inflate(context, R.layout.custom_view, this)
        imageView = findViewById(R.id.iv_option)
        textView = findViewById(R.id.tv_label)
    }

    fun setImage(id: Boolean) {
        val drawable = if (id) R.drawable.ic_accept else R.drawable.ic_reject
        imageView.setImageDrawable(ContextCompat.getDrawable(context, drawable))
    }

    fun handleClick(id: Boolean, click: () -> Unit) {

        imageView.setOnClickListener {
            val msg = if (id) "you are accepted" else "you are rejected"
            click.invoke()
            imageView.visibility = View.GONE
            textView.text = msg
        }
    }
}
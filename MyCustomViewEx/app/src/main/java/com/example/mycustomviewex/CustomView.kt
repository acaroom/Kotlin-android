package com.example.mycustomviewex

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.view_my_custom.view.*


class CustomView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.view_my_custom,this, true)
        orientation = VERTICAL

        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.CustomView,
                0, 0
            )
            val title = resources.getText(typedArray.getResourceId(
                R.styleable.CustomView_my_custom_title,
                R.string.custom_view1
            ))
            tv_title.text = title
            my_edit.hint = resources.getString(R.string.custom_view1)

            typedArray.recycle()
        }

    }

}
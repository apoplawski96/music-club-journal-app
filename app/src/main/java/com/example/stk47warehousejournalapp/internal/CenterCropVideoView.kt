package com.example.stk47warehousejournalapp.internal

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView
import android.view.View

class CenterCropVideoView(context : Context) : VideoView(context) {
    private var leftAdjustment: Int = 0
    private var topAdjustment: Int = 0

    public constructor(context: Context, attributeSet: AttributeSet) : this(context)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val videoWidth = measuredWidth
        val videoHeight = measuredHeight

        val viewWidth = View.getDefaultSize(0, widthMeasureSpec)
        val viewHeight = View.getDefaultSize(0, heightMeasureSpec)

        leftAdjustment = 0
        topAdjustment = 0
        if (videoWidth == viewWidth) {
            val newWidth = (videoWidth.toFloat() / videoHeight * viewHeight).toInt()
            setMeasuredDimension(newWidth, viewHeight)
            leftAdjustment = -(newWidth - viewWidth) / 2
        } else {
            val newHeight = (videoHeight.toFloat() / videoWidth * viewWidth).toInt()
            setMeasuredDimension(viewWidth, newHeight)
            topAdjustment = -(newHeight - viewHeight) / 2

        }
    }

    override fun layout(l: Int, t: Int, r: Int, b: Int) {
        super.layout(l + leftAdjustment, t + topAdjustment, r + leftAdjustment, b + topAdjustment)
    }
}
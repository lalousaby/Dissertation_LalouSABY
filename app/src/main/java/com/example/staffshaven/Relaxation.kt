package com.example.staffshaven

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import android.widget.Button
import android.widget.TextView

class Relaxation : Fragment() {

    private lateinit var circle: CardView
    private lateinit var startBtnCircle: Button
    private lateinit var endBtnCircle: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_relaxation, container, false)
        circle = view.findViewById(R.id.circle)
        startBtnCircle = view.findViewById(R.id.startBtnCircle)
        endBtnCircle = view.findViewById(R.id.endBtnCircle)

        val initialScaleX = 1.0f

        val scaleAnimator = ValueAnimator.ofFloat(initialScaleX, initialScaleX * 3, initialScaleX)

       startBtnCircle.setOnClickListener {
           scaleAnimator.apply {
               duration = 9500
               repeatCount = 9
           }

           // Update view properties based on animation values
           val updateListener = { animation: ValueAnimator ->
               val scale = animation.animatedValue as Float
               if (animation === scaleAnimator) {
                   circle.scaleX = scale
                   circle.scaleY = scale
               }
               circle.invalidate()
           }

           scaleAnimator.addUpdateListener(updateListener)

           scaleAnimator.startDelay = 500
           scaleAnimator.start()
       }
        // Stop the animation when the end button is clicked
        endBtnCircle.setOnClickListener {
            scaleAnimator.cancel()
        }

        return view
    }

}
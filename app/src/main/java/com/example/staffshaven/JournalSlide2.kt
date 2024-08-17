package com.example.staffshaven

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.activityViewModels

class JournalSlide2 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

    private lateinit var frameLayoutSlideAnimationYes : FrameLayout
    private lateinit var frameLayoutSlideAnimationNo : FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_journal_slide2, container, false)

        frameLayoutSlideAnimationYes = view.findViewById(R.id.frameLayoutSlideAnimationYes)
        frameLayoutSlideAnimationYes.setOnClickListener {
            viewModel.selectedStudyBtnSlide = R.id.btnStudyYesSlide
            viewModel.onStudyYesSelectedSlide()
            updateAnimationSelection()
        }
        frameLayoutSlideAnimationNo = view.findViewById(R.id.frameLayoutSlideAnimationNo)
        frameLayoutSlideAnimationNo.setOnClickListener {
            viewModel.selectedStudyBtnSlide = R.id.btnStudyNoSlide
            viewModel.onStudyNoSelectedSlide()
            updateAnimationSelection()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateAnimationSelection()
    }

    private fun updateAnimationSelection() {
        val selectedStudyBtnId = viewModel.selectedStudyBtnIdSlide

        frameLayoutSlideAnimationYes.isSelected = viewModel.isStudyYesSelectedSlide
        frameLayoutSlideAnimationNo.isSelected = viewModel.isStudyNoSelectedSlide
    }
}
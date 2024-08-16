package com.example.staffshaven

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _checkBoxStates = mutableMapOf<String, Boolean>()
    val checkBoxStates: Map<String, Boolean> = _checkBoxStates
    val _selectedOptions = mutableListOf<String>()
    val selectedOptions: List<String> = _selectedOptions

    val _dayTextJournalClick = MutableLiveData<String>("")
    val dayTextJournalClick : LiveData<String> = _dayTextJournalClick

    val _positive1JournalClick = MutableLiveData<String>("")
    val positive1JournalClick : LiveData<String> = _positive1JournalClick
    val _positive2JournalClick = MutableLiveData<String>("")
    val positive2JournalClick : LiveData<String> = _positive2JournalClick
    val _positive3JournalClick = MutableLiveData<String>("")
    val positive3JournalClick : LiveData<String> = _positive3JournalClick

    var selectedStudyBtn: Int? = null
    private val _selectedStudyBtnId = MutableLiveData<Int>()
    val selectedStudyBtnId:LiveData<Int> = _selectedStudyBtnId
    val isStudyYesSelected: Boolean
        get() = selectedStudyBtnId.value == R.id.frameLayoutClickAnimationYes

    val isStudyNoSelected: Boolean
        get() = selectedStudyBtnId.value == R.id.frameLayoutClickAnimationNo

    var selectedVeggiesBtn: Int? = null
    val _selectedOptionsJournaling = mutableListOf<String>()
    val selectedOptionsJournaling: List<String> = _selectedOptionsJournaling

    // for click journaling version
    private val _selectedRadioButtonId = MutableLiveData<Int>()
    val selectedRadioButtonId: LiveData<Int> = _selectedRadioButtonId

    // for click journaling version
    private val _sleepRatingClick = MutableLiveData<Float>()
    val sleepRatingClick: LiveData<Float> = _sleepRatingClick

    fun setCheckBoxState(key: String, isChecked: Boolean) {
        _checkBoxStates[key] = isChecked
    }

    fun getCheckBoxState(key: String): Boolean {
        return _checkBoxStates[key] ?: false
    }

    fun onRadioButtonSelected(checkedId: Int) {
        _selectedRadioButtonId.value = checkedId  // for click journaling version
    }

    fun onRatingChanged(rating: Float) {
        _sleepRatingClick.value = rating
    }

    fun onTextChanged(text: String) {
        _dayTextJournalClick.value = text
    }

    fun onPositive1TextChanged(text: String) {
        _positive1JournalClick.value = text
    }

    fun onPositive2TextChanged(text: String) {
        _positive2JournalClick.value = text}

    fun onPositive3TextChanged(text: String) {
        _positive3JournalClick.value = text
    }

    fun onStudyYesSelected() {
        _selectedStudyBtnId.value = R.id.frameLayoutClickAnimationYes
    }

    fun onStudyNoSelected() {
        _selectedStudyBtnId.value = R.id.frameLayoutClickAnimationNo
    }
}
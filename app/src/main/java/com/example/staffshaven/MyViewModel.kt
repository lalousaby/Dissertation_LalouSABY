package com.example.staffshaven

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {

    private val _checkBoxStates = mutableMapOf<String, Boolean>()
    val checkBoxStates: Map<String, Boolean> = _checkBoxStates
    val _selectedOptions = mutableListOf<String>()
    val selectedOptions: List<String> = _selectedOptions

    val dayTextJournalClick = MutableLiveData<String>("")
    var selectedStudyBtn: Int? = null
    var selectedVeggiesBtn: Int? = null
    val _selectedOptionsJournaling = mutableListOf<String>()
    val selectedOptionsJournaling: List<String> = _selectedOptionsJournaling

    fun setCheckBoxState(key: String, isChecked: Boolean) {
        _checkBoxStates[key] = isChecked
    }

    fun getCheckBoxState(key: String): Boolean {
        return _checkBoxStates[key] ?: false
    }
}
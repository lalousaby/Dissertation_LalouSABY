package com.example.staffshaven

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.activity.result.contract.ActivityResultContracts

class Emergency : Fragment() {

    private lateinit var btnCallNumber: ImageButton
    private lateinit var btnLinkTA: ImageButton
    private lateinit var btnLinkSWT: ImageButton
    private lateinit var btnCallContact: ImageButton
    private var numberNHSMHT = "03001230907"
    private var urlTA = "https://www.staffs.ac.uk/students/support/student-wellbeing-and-safeguarding/togetherall"
    private var urlSWT = "https://forms.office.com/pages/responsepage.aspx?id=8nivV33IZkS3u2tsyZ7RJDyrEMBwprVLigLHEKhqLwlUME9MU0g5TEs5RFJCRzZQSU1YMkxXOVNRQiQlQCN0PWcu"

    // Open contacts and select a contact number
    private val contactActivityResultLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data ?: return@registerForActivityResult
            val contactUri = data.data
            val contentResolver = requireActivity().contentResolver
            if (contactUri != null) {
                contentResolver.query(contactUri, arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER), null, null, null).use { cursor ->
                    if (cursor != null && cursor.moveToFirst()) {
                        val numberIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)
                        val number = cursor.getString(numberIndex)
                        callContact(number)
                    }else {
                        Log.d("SelectContact", "No phone numbers found for the contact")
                    }
                }
            }

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for emergency fragment

        return inflater.inflate(R.layout.fragment_emergency, container, false)
    }

    private fun selectContact() {
        val intent = Intent(Intent.ACTION_PICK).apply {
            type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
        }
        contactActivityResultLauncher.launch(intent)
    }

    private fun callContact(number: String) {
        val intent: Intent = Intent().apply {
            action = Intent.ACTION_DIAL
            data = Uri.parse("tel:$number")
        }
        startActivity(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnCallNumber = view.findViewById(R.id.callBtn)
        btnLinkTA = view.findViewById(R.id.linkTogetherAllBtn)
        btnLinkSWT = view.findViewById(R.id.linkAppointmentBtn)
        btnCallContact = view.findViewById(R.id.btnCallContact)

        // Set click listeners to call the number
        btnCallNumber.setOnClickListener {
           val intent: Intent = Intent().apply {
               action = Intent.ACTION_DIAL
               data = Uri.parse("tel:$numberNHSMHT")
           }
           startActivity(intent)
        }

        // Set click listeners to open links
        btnLinkTA.setOnClickListener {
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlTA)
            }
            startActivity(intent)
        }

        // Set click listeners to open links
        btnLinkSWT.setOnClickListener {
            val intent: Intent = Intent().apply {
                action = Intent.ACTION_VIEW
                data = Uri.parse(urlSWT)
            }
            startActivity(intent)
        }

        btnCallContact.setOnClickListener{
            selectContact()
        }
    }

    companion object {
        const val REQUEST_SELECT_PHONE_NUMBER = 1
    }

}
package com.example.staffshaven

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.activityViewModels

class FoodSlide1 : Fragment() {
    private val viewModel: MyViewModel by activityViewModels()

        private lateinit var btnImageB: ImageButton
        private lateinit var btnPhotoB: ImageButton
        private lateinit var breakfastImg: ImageView

        private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
        private lateinit var takePictureLauncher: ActivityResultLauncher<Intent>
        private lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>
        private val REQUEST_CAMERA_PERMISSION = 100
        private var clickedButtonId: Int = 0


        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            // Inflate the layout for this fragment
            // Inflate the layout for this fragment
            val view = inflater.inflate(R.layout.fragment_food_slide1, container, false)

            btnImageB = view.findViewById(R.id.btnImageB)
            btnPhotoB = view.findViewById(R.id.btnPhotoB)
            breakfastImg = view.findViewById(R.id.breakfastImg)


            // Associate a button with an ImageView
            pickImageLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val imageUri = result.data?.data ?: return@registerForActivityResult
                    val clickedButton = view.touchables
                        .firstOrNull { it.id == clickedButtonId }
                    if (clickedButton != null) {
                        val imageView = when (clickedButton.id) {
                            R.id.btnImageB -> breakfastImg
                            else -> null
                        }
                        if (imageView != null) {
                            imageView.setImageURI(imageUri)
                            viewModel.onBreakfastImageChanged(imageView.drawable.toBitmap())
                        } else {
                            Toast.makeText(requireContext(), "Unexpected button click", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(requireContext(), "Couldn't identify clicked button", Toast.LENGTH_SHORT).show()
                    }
                }
            }

            // Store the id of the clicked button and launch the pick image launcher to store the picture in the associated ImageView
            btnImageB.setOnClickListener { clickedButtonId = R.id.btnImageB; pickImageLauncher.launch(
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            ) }
            // Launch the camera to take a picture and store it in the associated ImageView
            takePictureLauncher = registerForActivityResult(
                ActivityResultContracts.StartActivityForResult()
            ) { result ->
                val imageBitmap = result.data?.extras?.get("data") as? Bitmap
                val clickedButton = view.touchables
                    .firstOrNull { it.id == clickedButtonId }
                if (result.resultCode == RESULT_OK && imageBitmap != null) {
                    if (clickedButton != null) {
                        when (clickedButton.id) {
                            R.id.btnPhotoB -> {
                                breakfastImg.setImageBitmap(imageBitmap)
                                viewModel.onBreakfastImageChanged(imageBitmap)
                            }
                            else -> null
                        }
                    }
                } else {
                    Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
                }

            }
            btnPhotoB.setOnClickListener { clickedButtonId = R.id.btnPhotoB; requestCameraPermission() }

            cameraPermissionLauncher = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    captureImage()
                } else {
                    Toast.makeText(requireContext(), "Can not access camera", Toast.LENGTH_SHORT).show()
                }
            }

            val editText = view.findViewById<EditText>(R.id.editBreakfastTxt)
            editText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    viewModel.onBreakfastTextChangedSlide(s.toString())
                }
            })

            viewModel.breakfastTextFoodSlide.observe(viewLifecycleOwner) { text ->
                if (editText.text.toString() != text) {
                    editText.setText(text)
                }
            }
            viewModel.breakfastImage.observe(viewLifecycleOwner) { image ->
                image?.let {
                    breakfastImg.setImageBitmap(it)
                }
            }

            return view
        }


        private fun captureImage() {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            if (intent.resolveActivity(requireActivity().packageManager) != null) {
                takePictureLauncher.launch(intent)
            } else {
                Toast.makeText(requireContext(), "Camera not available", Toast.LENGTH_SHORT).show()
            }
        }

        private fun requestCameraPermission() {
            cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
        }


}
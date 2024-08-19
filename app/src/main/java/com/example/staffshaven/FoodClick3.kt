package com.example.staffshaven

import android.Manifest
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts

class FoodClick3 : Fragment() {
    private lateinit var LArrowClick3 : ImageButton
    private lateinit var submitFoodClick : Button
    private lateinit var findFood: Button

    private lateinit var btnImageD: ImageButton
    private lateinit var btnPhotoD: ImageButton
    private lateinit var dinerImg: ImageView
    private lateinit var pickImageLauncher: ActivityResultLauncher<Intent>
    private lateinit var takePictureLauncher: ActivityResultLauncher<Intent>
    private lateinit var cameraPermissionLauncher: ActivityResultLauncher<String>
    private val REQUEST_CAMERA_PERMISSION = 100
    private var clickedButtonId: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_food_click3, container, false)

        btnImageD = view.findViewById(R.id.btnImageD)
        btnPhotoD = view.findViewById(R.id.btnPhotoD)
        dinerImg = view.findViewById(R.id.dinerImg)
        findFood = view.findViewById(R.id.findFood)

        LArrowClick3 = view.findViewById(R.id.LArrowClick3)
        LArrowClick3.setOnClickListener {
            val foodClick2Fragment = FoodClick2()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()
            transaction.replace(R.id.frame_layout, foodClick2Fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }

        submitFoodClick = view.findViewById(R.id.submitFoodClick)
        submitFoodClick.setOnClickListener {
            val FoodFrag = FoodSlide1()
            val transaction = requireActivity().supportFragmentManager.beginTransaction()

            transaction.replace(R.id.frame_layout, FoodFrag)
            transaction.commit()
        }

        findFood.setOnClickListener {
            goToFindFood()
        }

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
                        R.id.btnImageD -> dinerImg
                        else -> null
                    }
                    if (imageView != null) {
                        imageView.setImageURI(imageUri)
                    } else {
                        Toast.makeText(requireContext(), "Unexpected button click", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Couldn't identify clicked button", Toast.LENGTH_SHORT).show()
                }
            }
        }
        // Store the id of the clicked button and launch the pick image launcher to store the picture in the associated ImageView
        btnImageD.setOnClickListener { clickedButtonId = R.id.btnImageD; pickImageLauncher.launch(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)) }

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
                        R.id.btnPhotoD -> dinerImg.setImageBitmap(imageBitmap)

                        else -> null
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Failed to capture image", Toast.LENGTH_SHORT).show()
            }

        }
        btnPhotoD.setOnClickListener { clickedButtonId = R.id.btnPhotoD; requestCameraPermission() }


        cameraPermissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted ->
            if (isGranted) {
                captureImage()
            } else {
                Toast.makeText(requireContext(), "Can not access camera", Toast.LENGTH_SHORT).show()
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

    private fun goToFindFood(){
        val findFoodFragment = findFood()
        val transaction = requireActivity().supportFragmentManager.beginTransaction()

        transaction.replace(R.id.frame_layout, findFoodFragment)
        transaction.commit()
    }
}
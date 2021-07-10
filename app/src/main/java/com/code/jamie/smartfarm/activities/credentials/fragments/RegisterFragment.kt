package com.code.jamie.smartfarm.activities.credentials.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.code.jamie.smartfarm.activities.homepage.HomeActivity
import com.code.jamie.smartfarm.databinding.FragmentRegisterBinding
import com.code.jamie.smartfarm.utils.Utilities

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "RegisterFragment"
private const val EXTERNAL_STORAGE_CODE = 500
private const val GALLERY_CODE = 600
private var selectedImage:Uri? = null

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private var param1: String? = null
    private var param2: String? = null
//    private var imageUri: Uri?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        binding.imageView.setOnClickListener {
            if (isReadExternalStorageAllowed()) {
                goToGallery()
            } else {
                ActivityCompat.requestPermissions(
                    requireActivity(),
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    EXTERNAL_STORAGE_CODE
                )
            }

        }

        binding.register.setOnClickListener {
            val name = binding.nameEt.text.toString().trim()
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            val confirmPassword = binding.confirmPasswordEt.text.toString().trim()
            if (name.isEmpty()) {
                binding.nameEt.error = "Cannot be empty"
            } else {
                if (email.isEmpty()) {
                    binding.emailEt.error = "Cannot be empty"
                } else {
                    if (password.isEmpty()) {
                        binding.passwordEt.error = "Cannot be empty"
                    } else {
                        if (confirmPassword.isEmpty()) {
                            binding.confirmPasswordEt.error = "Cannot be empty"
                        } else {
                            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                                binding.emailEt.error = "Invalid Email Address"
                            } else {
                                if (password != confirmPassword) {
                                    binding.passwordEt.error = "Passwords do not match"
                                    binding.confirmPasswordEt.error = "Passwords do not match"
                                }else{
                                    if (selectedImage.toString().isEmpty()){
                                        Utilities().longToast(requireContext(),"Select a profile picture to proceed")
                                    }else{
                                        //todo: Perform registration
                                        Utilities().shortToast(requireContext(),"Performed register")
                                        startActivity(Intent(requireContext(),HomeActivity::class.java))
                                        requireActivity().finish()
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return binding.root
    }

    private fun goToGallery() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, GALLERY_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == EXTERNAL_STORAGE_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED &&
            permissions.contentEquals(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE))
        ) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, GALLERY_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == GALLERY_CODE && resultCode == Activity.RESULT_OK && data != null) {
            selectedImage = data.data!!
            try {
                val bitmap =
                    MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedImage)
                binding.imageView.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Log.e(TAG, "onActivityResult: Image not converted to bitmap")
            }
        }
    }

    private fun isReadExternalStorageAllowed(): Boolean {
        return ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegisterFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
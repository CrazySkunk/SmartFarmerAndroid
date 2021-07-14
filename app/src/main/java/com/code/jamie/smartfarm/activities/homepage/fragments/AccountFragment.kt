package com.code.jamie.smartfarm.activities.homepage.fragments

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.code.jamie.smartfarm.R
import com.code.jamie.smartfarm.adapters.AccountViewPagerAdapter
import com.code.jamie.smartfarm.adapters.ItemsViewPagerAdapter
import com.code.jamie.smartfarm.databinding.FragmentAccountBinding
import com.code.jamie.smartfarm.models.PagerItem
import com.code.jamie.smartfarm.utils.Utilities

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private const val TAG = "AccountFragment"

class AccountFragment : Fragment() {
    lateinit var binding: FragmentAccountBinding
    private var param1: String? = null
    private var param2: String? = null
    private var selectedImage: Uri? = null
    private var bitmap: Bitmap? = null
    lateinit var image: ImageView

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
        binding = FragmentAccountBinding.inflate(inflater, container, false)
        binding.editFab.setOnClickListener {
            Toast.makeText(requireContext(), "Edit fab clicked", Toast.LENGTH_SHORT).show()
        }
        val pagerItems = listOf<PagerItem>(
            PagerItem("Title", R.drawable.android_apps),
            PagerItem("Title", R.drawable.android_apps1),
            PagerItem("Title", R.drawable.android_apps),
            PagerItem("Title", R.drawable.android_apps2),
            PagerItem("Title", R.drawable.android_apps),
            PagerItem("Title", R.drawable.android_apps3),
            PagerItem("Title", R.drawable.android_apps),
        )
        val pagerAdapter = AccountViewPagerAdapter(pagerItems)
        binding.viewPagerMyPics.apply {
            adapter = pagerAdapter
        }
        binding.editFab.setOnClickListener {
            val view = layoutInflater.inflate(R.layout.edit_account_details_dialog,null)
            val nameEt:EditText = view.findViewById(R.id.name_et_dialog)
            val emailEt:EditText = view.findViewById(R.id.email_et_dialog)
            val passWrdEt:EditText = view.findViewById(R.id.password_et_dialog)
            val submit:Button = view.findViewById(R.id.submit_btn_dialog_acc)
            val image:ImageView = view.findViewById(R.id.image_view_dialog)
            val alertDialog = AlertDialog.Builder(requireContext())
            alertDialog.setTitle("Alert here")
            alertDialog.setView(view)
            val alert= alertDialog.create()
            image.setOnClickListener {
                if (canReadExternalStorage()){
                    val intent = Intent(Intent.ACTION_PICK)
                    intent.type="image/*"
                    startActivityForResult(intent,700)
                }else{
                    ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),100)
                }
            }
            submit.setOnClickListener {
                val nameStr = nameEt.text.toString().trim()
                val emailStr = emailEt.text.toString().trim()
                val passWrdStr = passWrdEt.text.toString().trim()
                Toast.makeText(requireContext(),"Name: $nameStr \nEmail: $emailStr\nPassword: $passWrdStr",Toast.LENGTH_LONG).show()
                if (!Patterns.EMAIL_ADDRESS.matcher(emailStr).matches()){
                    emailEt.error="Invalid email address"
                }else{
                    //todo: Update user details
                    alert.dismiss()
                }
            }

            alert.show()
        }
        return binding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 100 && permissions.contentEquals(arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)) && grantResults.isNotEmpty()) {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*";
            startActivityForResult(intent, 700)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 700 && resultCode == Activity.RESULT_OK && data != null) {
            selectedImage = data.getData()
            try {
                Toast.makeText(requireContext(),"path=${selectedImage!!.path}",Toast.LENGTH_LONG).show()
                bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, selectedImage)
                image.setImageBitmap(bitmap)
            } catch (e: Exception) {
                Log.i(TAG, "onActivityResult: ${e.message}")
            }
        }
    }

    private fun canReadExternalStorage(): Boolean {
        return if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            false
        } else ActivityCompat.checkSelfPermission(
            requireContext(),
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
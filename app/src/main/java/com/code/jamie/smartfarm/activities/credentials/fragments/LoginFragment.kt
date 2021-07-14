package com.code.jamie.smartfarm.activities.credentials.fragments

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.code.jamie.smartfarm.databinding.FragmentLoginBinding
import com.code.jamie.smartfarm.utils.Utilities
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding
    private var param1: String? = null
    private var param2: String? = null

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
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        binding.loginBtn.setOnClickListener {
            val email = binding.emailEt.text.toString().trim()
            val password = binding.passwordEt.text.toString().trim()
            if (email.isEmpty()) {
                binding.emailEt.error = "Cannot be empty"
            } else {
                if (password.isEmpty()) {
                    binding.passwordEt.error = "Cannot be empty"
                } else {
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        binding.emailEt.error = "Invalid Email Address"
                    } else {
                        //todo: Perform Login
                        Utilities().shortToast(requireContext(), "Performed Login")
                    }
                }
            }
        }
        binding.forgotPasswordEt.setOnClickListener {
            val recoverPassEmailEt = TextInputLayout(requireContext())
            recoverPassEmailEt.hint = "Enter email to reset send link to"
            val emailRecoverEt = TextInputEditText(requireContext())
            recoverPassEmailEt.addView(emailRecoverEt)
            val alertDialog = MaterialAlertDialogBuilder(requireContext())
                .setTitle("Password recovery")
                .setView(recoverPassEmailEt)
                .setPositiveButton("Submit") { _, _ ->
                    //todo: Send reset email to inbox
                    Utilities().shortToast(requireContext(), "send reset link")
                }
                .setCancelable(true)

            val alert = alertDialog.create()
            alert.show()
        }
        return binding.root
    }

    companion object {

        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
package com.example.pcmarketpro.presentation.forgotpassword

import android.app.Fragment
import android.os.Bundle
import android.view.View
import com.example.pcmarketpro.R
import com.example.pcmarketpro.common.Resource
import com.example.pcmarketpro.common.delegate.viewBinding

class ForgotPasswordFragment : Fragment(R.layout.fragment_forgot_password) {

    private val binding by viewBinding(FragmentForgotPasswordBinding::bind)

    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            btnSend.setOnClickListener {
                if (etEmail.isValidEmail(getString(R.string.invalid_mail)))
                    viewModel.sendPasswordResetEmail(etEmail.text.toString())
            }

            tvAlreadyAccount.setOnClickListener {
                it.findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }

            viewModel.result.observe(viewLifecycleOwner) {
                when (it) {
                    is Resource.Success -> {
                        requireView().showSnackbar(getString(R.string.email_sent))
                        progressBar.gone()
                    }

                    is Resource.Error -> {
                        progressBar.gone()
                        requireView().showSnackbar(getString(R.string.something_went_wrong))
                    }

                    Resource.Loading -> progressBar.visible()
                }
            }
        }
    }
}
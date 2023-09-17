package com.example.pcmarketpro.presentation.signup

import android.os.Bundle
import android.view.View

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {

    private val binding by viewBinding(FragmentSignUpBinding::bind)

    private val viewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initObservers()

        with(binding) {

            btnSignUp.setOnClickListener {
                if (checkInfos(etEmail, etPassword, etVerifyPassword, etNickname, etPhoneNumber)) {
                    viewModel.signUpWithEmailAndPassword(
                        User(
                            email = etEmail.text.toString(),
                            nickname = etNickname.text.toString(),
                            phoneNumber = etPhoneNumber.text.toString()
                        ),
                        etPassword.text.toString()
                    )
                }
            }

            tvAlreadyAccount.setOnClickListener {
                it.findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
            }
        }
    }

    private fun initObservers() {

        with(binding) {

            with(viewModel) {

                checkCurrentUser.observe(viewLifecycleOwner) {
                    if (it) findNavController().navigate(R.id.action_signUpFragment_to_main_graph)
                }

                result.observe(viewLifecycleOwner) {
                    when (it) {
                        is Resource.Success -> {
                            requireView().showSnackbar("Sign up successful")
                            findNavController().navigate(R.id.action_signUpFragment_to_main_graph)
                            progressBar.gone()
                        }

                        is Resource.Error -> {
                            progressBar.gone()
                            requireView().showSnackbar(it.throwable.message.toString())
                        }

                        Resource.Loading -> progressBar.visible()
                    }
                }
            }
        }
    }

    private fun checkInfos(
        email: TextInputEditText,
        password: TextInputEditText,
        verifyPassword: TextInputEditText,
        name: TextInputEditText,
        phoneNumber: TextInputEditText
    ): Boolean {
        val checkInfos = when {
            email.isValidEmail(getString(R.string.invalid_mail)).not() -> false
            name.isNullorEmpty(getString(R.string.invalid_nickname)).not() -> false
            phoneNumber.isNullorEmpty(getString(R.string.invalid_phone_number)).not() -> false
            password.isNullorEmpty(getString(R.string.invalid_password)).not() -> false
            verifyPassword.isNullorEmpty(getString(R.string.invalid_verify_password)).not() -> false
            password.text.toString() != verifyPassword.text.toString() -> false
            else -> true
        }
        return checkInfos
    }
}
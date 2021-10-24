package dev.kingbond.developerchat.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import dev.kingbond.developerchat.databinding.FragmentLoginBinding
import dev.kingbond.developerchat.model.ChatUser

class LoginFragment : Fragment() {

    private var bindingLogin: FragmentLoginBinding? = null
    private val binding get() = bindingLogin!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // layout inflation
        bindingLogin = FragmentLoginBinding.inflate(inflater, container, false)

        binding.button.setOnClickListener {
            authenticateTheUser()
        }
        return binding.root
    }

    // to validate & authenticate the user
    private fun authenticateTheUser() {
        val firstName = binding.firstNameEditText.text.toString()
        val username = binding.usernameEditText.text.toString()

        if (validateInput(firstName, binding.firstNameInputLayout) &&
            validateInput(username, binding.usernameInputLayout)
        ) {
            val chatUser = ChatUser(firstName, username)
            val action = LoginFragmentDirections.actionLoginFragmentToChannelFragment(chatUser)
            findNavController().navigate(action)
        }
    }

    // to validate the input in the field
    private fun validateInput(inputText: String, textInputLayout: TextInputLayout): Boolean {
        return if (inputText.length <= 3) {
            textInputLayout.isErrorEnabled = true
            textInputLayout.error = "Minimum 4 characters allowed"
            return false
        } else {
            textInputLayout.isErrorEnabled = false
            textInputLayout.error = null
            true
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        bindingLogin = null
    }

}
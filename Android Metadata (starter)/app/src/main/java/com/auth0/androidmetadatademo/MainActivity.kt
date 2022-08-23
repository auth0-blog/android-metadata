package com.auth0.androidmetadatademo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.google.android.material.snackbar.Snackbar
import com.auth0.androidmetadatademo.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    // Access to onscreen controls
    private lateinit var binding: ActivityMainBinding

    // Auth0 data
    private lateinit var account: Auth0
    private var user = User()

    // App and user status
    private var isJustLaunched = true
    private var userIsAuthenticated = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        account = Auth0(
            getString(R.string.com_auth0_client_id),
            getString(R.string.com_auth0_domain)
        )

        binding.buttonLogin.setOnClickListener { login() }
        binding.buttonLogout.setOnClickListener { logout() }

        updateUI()
    }

    private fun login() {
        WebAuthProvider
            .login(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(this, object : Callback<Credentials, AuthenticationException> {

                override fun onFailure(exception: AuthenticationException) {
                    // The user either pressed the “Cancel” button
                    // on the Universal Login screen or something
                    // unusual happened.
                    showSnackBar(getString(R.string.login_failure_message))
                }

                override fun onSuccess(credentials: Credentials) {
                    // The user successfully logged in.
                    val idToken = credentials.idToken
                    user = User(idToken)
                    userIsAuthenticated = true
                    showSnackBar(getString(R.string.login_success_message, user.name))
                    updateUI()
                }
            })
    }

    private fun logout() {
        WebAuthProvider
            .logout(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .start(this, object : Callback<Void?, AuthenticationException> {

                override fun onFailure(exception: AuthenticationException) {
                    // For some reason, logout failed.
                    showSnackBar(getString(R.string.general_failure_with_exception_code,
                        exception.getCode()))
                }

                override fun onSuccess(payload: Void?) {
                    // The user successfully logged out.
                    user = User()
                    userIsAuthenticated = false
                    updateUI()
                }

            })
    }

    private fun updateUI() {
        // Title
        if (isJustLaunched) {
            binding.textviewTitle.text = getString(R.string.initial_title)
            isJustLaunched = false
        } else {
            binding.textviewTitle.text = if (userIsAuthenticated) {
                getString(R.string.logged_in_title)
            } else {
                getString(R.string.logged_out_title)
            }
        }

        // Log in / log out buttons
        binding.buttonLogin.isVisible = !userIsAuthenticated
        binding.buttonLogout.isVisible = userIsAuthenticated

        // User information section
        binding.layoutUser.isVisible = userIsAuthenticated
        if (userIsAuthenticated) {
            binding.imageviewUser.loadImage(user.picture)
            binding.textviewUserProfile.text = getString(
                R.string.user_profile,
                user.name,
                user.email
            )
        }
    }


    // Utility functions
    // =================

    /**
     * This is a convenience method that simplifies the process
     * of displaying a SnackBar.
     *
     * @param text The text that the SnackBar should display.
     */
    private fun showSnackBar(text: String) {
        Snackbar
            .make(
                binding.root,
                text,
                Snackbar.LENGTH_LONG
            ).show()
    }

}
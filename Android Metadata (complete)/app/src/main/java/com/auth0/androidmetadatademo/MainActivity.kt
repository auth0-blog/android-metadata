package com.auth0.androidmetadatademo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.auth0.android.Auth0
import com.auth0.android.authentication.AuthenticationException
import com.auth0.android.callback.Callback
import com.auth0.android.management.ManagementException
import com.auth0.android.management.UsersAPIClient
import com.auth0.android.provider.WebAuthProvider
import com.auth0.android.result.Credentials
import com.auth0.android.result.UserProfile
import com.auth0.androidmetadatademo.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    // Access to onscreen controls
    private lateinit var binding: ActivityMainBinding

    // Auth0 data
    private lateinit var account: Auth0
    private var user = User()
    private var accessToken = ""

    // App and user status
    private var isJustLaunched = true
    private var userIsAuthenticated = false
    private var shouldDisplayAnnouncement = false
    private var announcementText = ""
    private var announcementUrl = ""


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
        binding.buttonRefreshAffirmation.setOnClickListener { getMetadata() }
        binding.buttonSaveAffirmation.setOnClickListener { updateUserMetadata() }

        updateUI()
    }

    private fun login() {
        WebAuthProvider
            .login(account)
            .withScheme(getString(R.string.com_auth0_scheme))
            .withAudience("https://${getString(R.string.com_auth0_domain)}/api/v2/")
            .withScope("openid profile email read:current_user update:current_user_metadata")
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
                    accessToken = credentials.accessToken
                    getMetadata()
                    userIsAuthenticated = true
                    showSnackBar(getString(R.string.login_success_message, user.name))
                    updateUI()
                }
            })
    }

    private fun getMetadata() {
        // Guard against getting the metadata when the user isn’t logged in.
        if (accessToken == "") {
            return
        }

        val usersClient = UsersAPIClient(account, accessToken)

        usersClient
            .getProfile(user.id)
            .start(object : Callback<UserProfile, ManagementException> {

                override fun onFailure(exception: ManagementException) {
                    showSnackBar(getString(R.string.general_failure_with_exception_code,
                        exception.getCode()))
                }

                override fun onSuccess(userProfile: UserProfile) {
                    // Get user metadata
                    val userMetadata = userProfile.getUserMetadata()
                    val personalAffirmation = userMetadata
                                                  .getOrDefault("personal_affirmation",
                                                                "") as String
                    binding.edittextPersonalAffirmation.setText(personalAffirmation)

                    // Get app metadata
                    val appMetadata = userProfile.getAppMetadata()
                    shouldDisplayAnnouncement = appMetadata
                                                    .getOrDefault("display_announcement",
                                                                  false) as Boolean
                    if (shouldDisplayAnnouncement) {
                        announcementText = appMetadata.getOrDefault("announcement_text",
                                                                    "") as String
                        announcementUrl = appMetadata.getOrDefault("announcement_url",
                            "") as String
                    }

                    updateUI()
                }

            })
    }

    private fun updateUserMetadata() {
        // Guard against setting the the metadata when the user isn’t logged in.
        if (accessToken == "") {
            return
        }

        val usersClient = UsersAPIClient(account, accessToken)
        val metadata = mapOf("personal_affirmation" to binding.edittextPersonalAffirmation.text.toString().trim())

        usersClient
            .updateMetadata(user.id, metadata)
            .start(object : Callback<UserProfile, ManagementException> {

                override fun onFailure(exception: ManagementException) {
                    showSnackBar(getString(R.string.general_failure_with_exception_code,
                        exception.getCode()))
                }

                override fun onSuccess(profile: UserProfile) {
                    showSnackBar(getString(R.string.general_success_message))
                }

            }) // start()
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

        // Personal affirmation section
        binding.layoutPersonalAffirmation.isVisible = userIsAuthenticated

        // Announcement section
        binding.buttonAnnouncement.isVisible = userIsAuthenticated &&
                                               shouldDisplayAnnouncement &&
                                               announcementText != "" &&
                                               announcementUrl != ""
        if (shouldDisplayAnnouncement) {
            binding.buttonAnnouncement.text = announcementText
            binding.buttonAnnouncement.setOnClickListener {
                val browserIntent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(announcementUrl))
                startActivity(browserIntent)
                }
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
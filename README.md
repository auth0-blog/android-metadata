# Companion projects for _Working with Auth0 User and App Metadata in Android Apps_

## About this project

This repository contains starter and completed projects that accompany the Auth0 blog article, [**_Working with Auth0 User and App Metadata in Android Apps_**](https://auth0.com/blog/working-auth0-user-app-metadata-android-apps/).

The article shows the reader how to use Auth0’s Management API to access custom data from the user profile’s user metadata and app metadata in an Android app written in Kotlin. These projects are the initial and final version of the app featured in the article.

The project is for a single-screen Android app that allows users to log in and log out. When logged in, the app allows the user to view and update a setting stored in their user metadata. The app also presents an announcement based on data stored in the user’s app metadata. The app was purposely kept as simple as possible to keep the focus on authentication.


## A quick tour of the app

When you launch the app, the initial screen greets you with the app’s title, _Metadata demo_, and a _Log in_ button:

![Opening screen, with “Metadata demo” in title font and “Log in” button below it.](https://images.ctfassets.net/23aumh6u8s0i/6eewi6iXnuI8vZyLVzTfiB/a99b35689f43f054644b8a45d29f1162/starter_screen_1.png)

Press the _Log in_ button. This will take you to the [Auth0 Universal Login screen](https://auth0.com/docs/login/universal-login), which appears in a web browser view embedded in the app:

![Universal Login screen, part 1: Entering the email address.](https://images.ctfassets.net/23aumh6u8s0i/4jeMWIsfLAEVUhF6a2rbF3/1b627462b8b8fb720af637ffe3158d0a/starter_screen_2.png)

Once you’ve entered your email address, you’ll proceed to the second part of the Universal Login screen, where you’ll enter your password:

![Universal Login screen, part 2: Entering the password.](https://images.ctfassets.net/23aumh6u8s0i/7wG5yxnGV8Ffof8zrrly6t/093aff59a94da57935db0c6dd5f035e4/starter_screen_3.png)

Whenever a user uses Universal Login to authenticate into an app for the first time, they see this screen, which asks for permission to use information from their user account:

![Universal Login screen, part 4: “Authorize App” screen.](https://images.ctfassets.net/23aumh6u8s0i/3TL1FrGH4N43F3QLXAOgLH/a807c1b64448d935e20164fd32a58e7d/authorize_app.png)

Press the _Accept_ button to continue. This completes the login process, taking you to the app’s main screen, which displays the information from your user account:

![“You’re logged in!” screen for user “randomuser@example.com”, showing the user’s name, email, and empty “personal affirmation” text field.](https://images.ctfassets.net/23aumh6u8s0i/5HThYnUTltuLCuDQW2HU55/8ab6c9c921985223aac2a0fb7d337b00/complete_screen_1.png)

As you can see, the app displays your name, email address, and the photo automatically generated for your account when you created it.

It also displays your personal affirmation — user metadata — that you can edit. The text field will be empty if you haven’t entered an affirmation yet. If you have, it will appear in the text field when the app starts up and whenever you press the _Refresh affirmation_ button:

![“You’re logged in!” screen for user “randomuser@example.com”, showing the user’s name, email, and filled-in “personal affirmation” text field.](https://images.ctfassets.net/23aumh6u8s0i/1GzaMkouDzsTDYhZx2mYEF/75c4ae61f8680d37d855d12a18b97e23/complete_screen_2.png)

The app also uses app metadata to determine if it should show an “announcement” web button. The app metadata also determines the text for the button and the URL for the web page it should open:

![“You’re logged in!” screen for user “skippy@example.com”, showing the user’s name, email, filled-in “personal affirmation” text field, and “Tap here for an important announcement” button.](https://images.ctfassets.net/23aumh6u8s0i/2A77zS6gM0WOn8h0r9MJkO/dcc9cc101e00904d25f3848056cfb702/complete_screen_3.png)

If you press the _Tap here for an important announcement_ button, the app opens a YouTube video containing that announcement, delivered by 1980s pop star Rick Astley: 

![Rick Astley’s “Never Gonna Give You Up” video on YouTube.](https://images.ctfassets.net/23aumh6u8s0i/78MAP7qp6sMiYPshyjcg0W/8806be45ab082f91bcb80736b074541b/complete_screen_4.png)

When you press the _Log out_ button, you go back to the app’s initial screen, which now displays “You’re logged out” as the title text:

![“Logged out” screen, with “You’re logged out.” in title font and “Log in” button below it.](https://images.ctfassets.net/23aumh6u8s0i/4Tr87FxmHYuzJXmdMsUTrF/7303375181f0d5349e6c865b4b16c711/starter_screen_4.png)


## How to install and run the projects

You’ll need the following to build the app:


### 1. An Auth0 account

The app uses Auth0 to authenticate users, which means that you need an Auth0 account. You can <a href="https://auth0.com/signup" data-amp-replace="CLIENT_ID" data-amp-addparams="anonId=CLIENT_ID(cid-scope-cookie-fallback-name)">sign up for a free account</a>, which lets you add login/logout to 10 applications, with support for 7,000 users and unlimited logins. This should suit your prototyping, development, and testing needs.


### 2. An Android development setup

- Any computer running Linux, macOS, or Windows from 2013 or later with at least 8 GB RAM. When it comes to RAM, more is generally better.
- [**Java SE Developer Kit (JDK), version 11 or later.**](https://www.oracle.com/java/technologies/javase-jdk16-downloads.html) You can find out which version is on your computer by opening a command-line interface and entering `java --version`.
- [**Android Studio,**](https://developer.android.com/studio) version 3.6 (February 2020) or later. I used the current stable version of Android Studio when writing this article: version 2021.2.1, also known as “Chipmunk.”
- **At least one Android SDK (Software Development Kit) platform.** You can confirm that you have one (and install one if you don’t) in Android Studio. Open _Tools_ → _SDK Manager_. You’ll see a list of Android SDK platforms. Select the current SDK (Android 11.0 (R) at the time of writing), click the _Apply_ button, and click the _OK_ button in the confirmation dialog that appears. Wait for the SDK platform to install and click the _Finish_ button when installation is complete.


### 3. An Android device, virtual or real

- **Using a real device:** Connect the device to your computer with a USB cable. Make sure that your device has Developer Options and USB debugging enabled.
- **Using a virtual device:** Using Android Studio, you can build a virtual device (emulator) that runs on your computer. Here’s my recipe for a virtual device that simulates a current-model inexpensive Android phone:
	1. Open _Tools_ → _AVD Manager_ (AVD is short for “Android Virtual Device”). The _Your Virtual Devices_ window will appear. Click the _Create Virtual Device..._ button.
	2. The _Select Hardware_ window will appear. In the _Phone_ category, select _Pixel 3a_ and click the _Next_ button.
	3. The _System Image_ window will appear, and you’ll see a list of Android versions. Select _R_ (API 30, also known as Android 11.0). If you see a _Download_ link beside _R_, click it, wait for the OS to download, then click the _Finish_ button. Then click the _Next_ button.
	4. The _Android Virtual Device (AVD)_ window will appear. The _AVD Name_ field should contain _Pixel 3a API 30_; the two rows below it should have the titles _Pixel 3a_ (a reasonable “representative” phone, released three years ago at the time of writing) and _R_, and in the _Startup orientation_ section, _Portrait_ should be selected. Click the _Finish_ button.
	5. You will be back at the _Your Virtual Devices_ window. The list will now contain _Pixel 3a API 30_, and that device will be available to you when you run the app.


### Installing and running the starter project

This project is meant to be the starting point for the article’s exercise. It’s not complete, but it _will_ run.

To run the starter project, download it and follow the article’s instructions.


### Installing and running the complete project

The complete project is the result of downloading the start project and following the article’s instructions. It is provided for reference.

To use the complete project, download it, then connect it to Auth0 by doing the following:

* Log into the [Auth0 dashboard](https://manage.auth0.com/dashboard/), select _Applications_ → _Applications_ from the left side menu, then click the _Create Application_ button.
* Enter a name for the app in the _Name_ field and choose the _Native_ application type.
* Select the _Settings_ tab and copy the _Domain_ and _Client ID_ values.
* Open `Auth0.plist` in the app project. Paste the _Domain_ value that you just copied into the _Value_ field of the property list’s `Domain` row.
* Paste the _Client ID_ value that you just copied into the _Value_ field of the property list’s `ClientId` row.
* Copy the project’s Bundle Identifier from Xcode.
* Using the string below, replace `{YOUR_DOMAIN}` with your tenant’s domain and {YOUR_APP_PACKAGE_NAME} with the app’s package name (if you haven’t changed it, it should be `com.auth0.androidmetadata`:

```
app://{YOUR_DOMAIN}/android/{YOUR_APP_PACKAGE_NAME}/callback
```

You will also need to create a new user with user and app metadata. Follow these steps:

* * Log into the [Auth0 dashboard](https://manage.auth0.com/dashboard/), select _User Management_ → _Users_ from the left side menu, then click the _Create User_ button.
* Enter an email address for the user in the _Email_ field, enter a password for the user in both the _Password_ and _Repeat Password_ fields, leave _Connection_ set to _Username-Password-Authentication_ and click the _Create_ button. This will create a new user and take you to that user’s _Details_ page.
* Scroll down the _Details_ page to the _Metadata_ section.
* Enter the following into the _user\_metadata_ text area:

```json
{
  "personal_affirmation": "Believe in yourself!"
}
```

* Enter the following into the _app\_metadata_ text area:

```json
{
  "display_announcement": true,
  "announcement_text": "Tap here for an important announcement",
  "announcement_url": "https://www.youtube.com/watch?v=DLzxrzFCyOs"
}
```

* Click the _Save_ button.

* **Run the app!**


## License

Copyright (c) 2022 [Auth0](http://auth0.com)

Licensed under the [Apache License, version 2.0](https://opensource.org/licenses/Apache-2.0).
# Companion projects for _Working with Auth0 User and App Metadata in Android Apps_

## About this project

This repository contains starter and completed projects that accompany the Auth0 blog article, [**_Working with Auth0 User and App Metadata in Android Apps_**](https://auth0.com/blog/working-auth0-user-app-metadata-android-apps/).

The article shows the reader how to use Auth0’s Management API to access custom data from the user profile’s user metadata and app metadata in an Android app written in Kotlin. These projects are the initial and final version of the app featured in the article.

The project is for a single-screen Android app that allows users to log in and log out. When logged in, the app allows the user to view and update a setting stored in their user metadata. The app also presents an announcement based on data stored in the user’s app metadata. The app was purposely kept as simple as possible to keep the focus on authentication.


## A quick tour of the app

When you launch the app, the initial screen greets you with the app’s title, _Metadata demo_, and a _Log in_ button:

![Opening screen, with “Metadata demo” in title font and “Log in” button below it.](https://images.ctfassets.net/23aumh6u8s0i/7vndxtjzjvZergPPK2SraE/3cb5987a350627318b07e31846b5b179/starter_screen_1.png)

Press the _Log in_ button to log into the app. The first thing you’ll see is this alert box:

![“‘iOS SwiftUI Metadata’ Wants to Use ‘auth0.com’ to Sign In” alert box.](https://images.ctfassets.net/23aumh6u8s0i/47OcYsux9USeY3h9LkUkXd/d68b8a305439f3b4a9a9056e6c230f8f/starter_screen_2.png)

iOS’ privacy policy requires it to inform the user when an app is sending or receiving personal information about them from a third party (Auth0 in this case), which it does with the alert box.

Press the _Continue_ button. This will take you to the [Auth0 Universal Login screen](https://auth0.com/docs/login/universal-login), which appears in a web browser view embedded in the app, where you enter your email address and password:

![Universal Login screen, part 1: Entering the email address.](https://images.ctfassets.net/23aumh6u8s0i/2lvbR45kgrMSr0TGxUmv5i/fd6d7814492b31a3174c674a5a1c65bc/starter_screen_3.png)

Once past the Universal Login screeen and related follow-up screens, you’re taken to the “logged in” screen, which displays your picture, name, email address, and personal affirmation:

![“You’re logged in!” screen for user “randomuser@example.com”, showing the user’s name, email, and filled-in “personal affirmation” text field.](https://images.ctfassets.net/23aumh6u8s0i/uUnKzMN7qJpmD95UE3qYz/6903d77eb11089eca00a09f070657932/complete_screen_8.png)

Your personal affirmation is stored in your user profile’s user metadata, and you can update it by entering an affirtmation in the text field and pressing the _Save affirmation_ button. You can also retrieve your current affirmation by pressing the _Refresh affirmation_ button.

The app also uses app metadata to determine if it should show an “announcement” web link. The app metadata also determines the text and URL for the link:

![“You’re logged in!” screen for user “skippy@example.com”, showing the user’s name, email, filled-in “personal affirmation” text field, and “Tap here for an important announcement” link.](https://images.ctfassets.net/23aumh6u8s0i/334xQBVhg8lzHog02QOMbJ/926f81a8a9c0258c1d3fb8ddaf8003cb/complete_screen_9.png)

If you tap the _Tap here for an important announcement_ link, the app opens a YouTube video containing that announcement, delivered by 1980s pop star Rick Astley: 

![Rick Astley’s “Never Gonna Give You Up” video on YouTube.](https://images.ctfassets.net/23aumh6u8s0i/6wiQ6bEOGkmc56PWdvABtF/1a07ef4965142501957c15a420293040/complete_screen_10.png)

When you log out, you’re taken back to the initial screen, which now displays “You’re logged out.” as its title:

![“Logged out” screen, with “You’re logged out.” in title font and “Log in” button below it.](https://images.ctfassets.net/23aumh6u8s0i/2i4ACeHwoGIlFCWHmonN0b/e5e0d353d936749db4843f7442484ae5/complete_screen_12.png)


## How to install and run the projects

You’ll need the following:

1. **An Auth0 account.** The app uses Auth0 to provide authenticate users, which means that you need an Auth0 account. You can <a href="https://auth0.com/signup" 
  data-amp-replace="CLIENT_ID" 
  data-amp-addparams="anonId=CLIENT_ID(cid-scope-cookie-fallback-name)">
  sign up for a free account</a>, which lets you add login/logout to 10 applications, with support for 7,000 users and unlimited logins. This should suit your prototyping, development, and testing needs.
2. **An iOS/iPadOS development setup:** 
	* Any Mac computer from 2013 or later — MacBook, MacBook Air, MacBook Pro, iMac, iMac Pro, Mac Mini, Mac Pro, or Mac Studio — with at least 8 GB RAM. When it comes to RAM, more is generally better.
	- Apple’s developer tool, Xcode version 11.0 (September 2019) or later. When writing this article, I used the current version at the time: 13.4.1 (build 13F100), released on June 2, 2022.
3. **3. An iOS device, virtual or real.** Xcode comes with the Simulator application, which simulates recent iPhone, iPad, and iPod Touch models. Xcode 13.4’s virtual devices run iOS 15.5 by default.


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
* Using the string below, replace `{BUNDLE_IDENTIFIER}` with the app’s bundle identifier and `{YOUR_DOMAIN}` with your tenant’s domain:

```
{BUNDLE_IDENTIFIER}://{YOUR_DOMAIN}/ios/{BUNDLE_IDENTIFIER}/callback
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
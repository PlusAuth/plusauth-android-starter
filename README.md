<div align="center">
  <a href="https://plusauth.com/">
    <img src="https://docs.plusauth.com/images/pa-white.svg" alt="Logo" width="320" height="72" >
  </a>
</div>

<h1 align="center">PlusAuth Android Starter Project</h1>

 <p align="center">
    Simple Android project demonstrates basic authentication flows with PlusAuth
    <br />
    <br />
    <a href="https://docs.plusauth.com/quickStart/spa/vue" target="_blank"><strong>Explore the PlusAuth Android docs »</strong></a>
</p>

<details>
  <summary>Table of Contents</summary>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#prerequisites">Prerequisites</a></li>
    <li><a href="#getting-started">Getting Started</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#what-is-plusauth">What is PlusAuth</a></li>
 </ol>
</details>

---

## About The Project

It is a very simple Android project demonstrating basic authentication flows such as register, login, and logout. To keep things simple, we used [PlusAuth OpenId Connect Library For Android](https://github.com/PlusAuth/plusauth-oidc-android) for authentication.

## Prerequisites

Before running the project, you must first follow these steps:

1. Create a PlusAuth account and a tenant at [PlusAuth Dashboard](https://dashboard.plusauth.com)
2. Navigate to [Clients](https://dashboard.plusauth.com/~clients) tab and create a client of type `Native Application`
3. Go to the details page of the client that you've just created and set the following fields as:

- Redirect Uris:  `com.plusauth.androidexample:/callback`
- Post Logout Redirect Uris:  `com.plusauth.androidexample:/callback`

Finally, note your Client Id and domain(e.g., https://example.plusauth.com) for app configuration on the next step.

## Getting Started

Edit the `PlusAuthInstance.java` class using your Client Id and PlusAuth domain.

That's all! Hit the 'Run' button in Android Studio to start the app. 

## License

This project is licensed under the MIT license. See the [LICENSE](LICENSE) file for more info.


## What is PlusAuth

PlusAuth helps individuals, teams, and organizations to implement authorization and authentication systems in a secure, flexible and easy way.

<a href="https://plusauth.com/" target="_blank"><strong>Explore the PlusAuth Website »</strong></a>

<a href="https://docs.plusauth.com/" target="_blank"><strong>Explore the PlusAuth Docs »</strong></a>

<a href="https://forum.plusauth.com/" target="_blank"><strong>Explore the PlusAuth Forum »</strong></a>

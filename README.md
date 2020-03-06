# app sign in

Nearly every android application has it, A sign in screen. Whether it's asking for an email or password, or a 4 digit pin.
 User Authentication is everywhere. And thats where app sign in library comes in. This is an Android Library that allows a developer to seamlessly integrate a functioning sign in module
into their already existing android application.


Email Password Sign In             |  Pin Sign In
:-------------------------:|:-------------------------:
![](https://user-images.githubusercontent.com/8895134/74273062-d53c1c00-4d20-11ea-9088-d7400337bbf0.png)  |  ![](https://user-images.githubusercontent.com/8895134/74273067-d66d4900-4d20-11ea-8292-22b483608cbe.png)

Biometric Sign In             |
:-------------------------:|
![](https://user-images.githubusercontent.com/8895134/74273070-d79e7600-4d20-11ea-8033-4694348bb5d9.png)  |

## Getting Started

This module is designed to be as seamless as possible and should work as a plug and play to your existing android application
requiring minimal modification.

## How it Works

The library uses a builder design pattern that allows you to specify which of the three sign in
 options you would like to add to your android project The benefit is that is allows you to state
  any additional sign in options your application can support. Thus making your user feel limitless.

### Prerequisites

What things you need to install the software and how to install them

```
Android Studio

A working Android Project

Minimum sdk used :- 16

```

### Installing

Use the following steps to get up and running as fast as possible and get back to building cool content.

#### Gradle

```
//on your project wide build.gradle file, add the maven repo link

allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

Then next step is to add the library dependency on your app build.grade file

```
//on your app build.gradle file, add the sign in ui

dependencies {
	        implementation 'com.github.Jackwitwicky:app_sign_in:Tag'
	}
```

#### Customization

To actually use the library. Add the following piece of code in your calling activity


```
SignInUI.Builder(this)
                .setSignInType(SignInUI.EMAIL_PASSWORD_FORM)
                .build()

```

That's it! That is all you need to display a sign in screen that prompts the user for their email and password.
 The library will automatically handle any form validations
 
 However the library offers multiple configurations you can use setup authentication properly
 

```
                .setPinSignInEnabled(true) - This is used when creating email or fingerprint and would like to also offer the pin 
                .setFingerprintSignInEnabled(true) - This is used when creating email or pin and would like to also offer the fingerprint
                .setPasswordLength(6) - This is used to specifiy the validation requirements for the email passsowrd page
                .setTitle("Logger")
                .setSubtitle("The best log tracking app")

```
### NB
The builder class expects a sign_in _type parameter to be passed as a minimum.
 Attempting to build an SignInUi without having specified the sign_in_type results in a Illegal State Exception 

## Built With

* [Ubuntu 18.04.3(https://www.ubuntu.com/desktop/) - Operating System Used
* [Android Studio](https://developer.android.com/studio/index.html) - Development Environment
* Love - Key ingredient

## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us.

## Author

 **Jack Kiarie** - *Initial work* - [Other Works](https://incobeta.com)
 **Daniel Katungi** - *Assisted work* - [Other Works](https://www.github.com/katungi)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details



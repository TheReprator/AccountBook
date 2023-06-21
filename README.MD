# Compose Multiplatform Application

## Before running!
- check your system with [KDoctor](https://github.com/Kotlin/kdoctor)
- install JDK 8 on your machine
- add `local.properties` file to the project root and set a path to Android SDK there
- run `./gradlew podInstall` in the project root

### Android
To run the application on android device/emulator:
- open project in Android Studio and run imported android run configuration

To build the application bundle:
- run `./gradlew :composeApp:assembleDebug`
- find `.apk` file in `composeApp/build/outputs/apk/debug/composeApp-debug.apk`

### Desktop
Run the desktop application: `./gradlew :composeApp:run`

### iOS
To run the application on iPhone device/simulator:
- Open `iosApp/iosApp.xcworkspace` in Xcode and run standard configuration
- Or use [Kotlin Multiplatform Mobile plugin](https://plugins.jetbrains.com/plugin/14936-kotlin-multiplatform-mobile) for Android Studio

### Browser
Run the browser application: `./gradlew :app:web:jsBrowserDevelopmentRun`

## Issues Pending

- [ ]  fetch error in webapp(currently get on loading), if api is not running
- [ ]  Test case for js browser application not running for first time, will start running from 2nd time
- [ ]  not working in case of ios


## License

```
Copyright Sasikanth Miriyampalli

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
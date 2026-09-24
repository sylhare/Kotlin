# todo-app

A minimal Android todo app in Kotlin, used to demo core Android patterns: single-activity
Jetpack Compose UI, `ViewModel` + `StateFlow` (MVVM, unidirectional data flow), and
instrumented E2E tests written with the Robot pattern.

## Stack

- Kotlin, Jetpack Compose (Material 3)
- AGP 9.3.1, Gradle 9.7, JDK 21
- compileSdk / targetSdk 37, minSdk 26

## Layout

```
app/src/main/kotlin/com/demo/todo/
    MainActivity.kt      # Compose UI (stateless TodoScreen + TodoRow)
    TodoViewModel.kt     # state (StateFlow<List<Todo>>) + add/toggle/remove
app/src/test/            # JVM unit tests (TodoViewModel)
app/src/androidTest/     # E2E tests + TodoRobot
```

## Prerequisites

Android SDK installed with `ANDROID_HOME` set, e.g.:

```bash
export ANDROID_HOME=~/Library/Android/sdk
```

`local.properties` must point `sdk.dir` at the SDK.

## Build

```bash
./gradlew :app:assembleDebug            # APK at app/build/outputs/apk/debug/
./gradlew installDebug                  # install on a running device/emulator
```

## Unit tests

JVM only, no device needed:

```bash
./gradlew :app:testDebugUnitTest
```

## E2E tests

Instrumented tests run on an emulator or device.

### Create and start an emulator

```bash
SDK="$ANDROID_HOME/cmdline-tools/latest/bin"

$SDK/sdkmanager "emulator" "platform-tools" "system-images;android-36;google_apis;arm64-v8a"
$SDK/avdmanager create avd -n TodoTest -k "system-images;android-36;google_apis;arm64-v8a" -d pixel_6

$ANDROID_HOME/emulator/emulator -avd TodoTest &
$ANDROID_HOME/platform-tools/adb wait-for-device
```

(Use `x86_64` images on Intel/Linux.)

### Run

```bash
./gradlew :app:connectedDebugAndroidTest
```

Target a specific device when several are connected:

```bash
ANDROID_SERIAL=emulator-5554 ./gradlew :app:connectedDebugAndroidTest
```

Run one class or test:

```bash
./gradlew :app:connectedDebugAndroidTest \
    -Pandroid.testInstrumentationRunnerArguments.class=com.demo.todo.TodoE2ETest
./gradlew :app:connectedDebugAndroidTest \
    -Pandroid.testInstrumentationRunnerArguments.class=com.demo.todo.TodoE2ETest#toggleTodo_marksItDone
```

Reports: `app/build/reports/androidTests/connected/`.

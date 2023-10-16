# FTC Robot 2023

This project contains the code used by the BotNotFound team for the 2023-2024 CENTERSTAGE competition

**IMPORTANT: All code for the robot is in the [`TeamCode` project](./TeamCode)!  The `FtcRobotController` project is just templates and code there WILL NOT be built or uploaded to the Control Hub!**

## Requirements

To use the project, you need:

- [Android Studio](https://developer.android.com/studio/) for development
- [REV Hardware Client](https://docs.revrobotics.com/rev-hardware-client/) for wireless uploading of builds to the Control Hub

### What if I don't have Android Studio?

You can find command-line tools for Android development [here](https://developer.android.com/studio).  The download links are at the very bottom of the page.
After unzipping the tools, open a terminal in the `bin/` folder and enter the following command:

<code>
sdkmanager --install platforms;android-34 --sdk_root="the path to an empty folder that will contain your Android SDK"
</code>

This should install the Android SDK to folder that you want.
After that is done, make a new file at the root of the repostiory called `local.properties` with the following contents:

<code>
sdk.dir="Absolute path of the folder containing the Android SDK"
</code>

With that set up, just open a terminal in the root of the repository and enter the command `.\gradlew build` to build the project.

## Help

If you're having trouble getting started, here are a few sites you can go to for info:

- [Game Manual 0](https://gm0.org/): general information on FTC.  Most of it is for hardware, but it has some useful tutorials for getting started
- [Tech Toolbox](https://ftc-tech-toolbox.vercel.app/): this has guides for almost everything you want to do in terms of software
- ...And as always, if you don't find an answer from anything above, a quick search online never hurts
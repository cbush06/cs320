# CS320 - Software Testing, Automation, and Quality Assurance

This project accompanies a video series created to help my Southern New Hampshire University CS320 class.

If you want to follow along with the video series (highly encouraged), clone the `starter` branch. The `master` branch is the end result of the video series.

## Video Series

You can find the video series at my YouTube playlist: https://www.youtube.com/playlist?list=PL5Qak8r8wDYplRHuvXAm7IDMmoQymDd-8

The video series includes:

- **Video 1:** Cloning this project and importing it into Eclipse
- **Video 2:** Coding and testing the `Contact` entity
- **Video 3:** Coding and testing the `ContactService` entity repository

## Prerequisites

> [!IMPORTANT]
> You can safely ignore this section if you are using SNHU's Apporto virtual desktop.

If you choose to follow along in Eclipse on your personal computer, you should ensure these prerequisites are met:

- [ ] You have [Java 8 or later](https://aws.amazon.com/corretto) installed
- [ ] You have a recent version of [Gradle](https://gradle.org/install/) installed
- [ ] You have a recent version of [Git](https://git-scm.com/) installed
- [ ] You have installed [Eclipse IDE for Java Developers](https://www.eclipse.org/downloads/packages/)

## Getting Started

> [!CAUTION]
> Only follow these instructions if you are using your personal computer. If you are using SNHU's Apporto virtual desktop, follow the instructions in the video.

1. Using a Bash/Zsh terminal, navigate to the directory you want to store your project in. For example:

   ```bash
   mkdir ~/projects
   cd projects
   ```

2. Clone the project:

   ```bash
   git clone https://github.com/cbush06/cs320.git
   ```

3. Checkout the starter branch:

   ```bash
   git checkout starter
   ```

4. Import the Gradle project into Eclipse:
   1. Open Eclipse
   2. Choose `File > Import...`
   3. Select `Grade > Existing Gradle Project`
   4. Select the cloned `cs320` directory for `Project Root Directory`
   5. Click `Finish`
   6. Choose `Window > Show View > Other...`
   7. Choose `Gradle > Gradle Tasks`
   8. Expand `cs320 > build`
   9. Double-click on the `build` task
   10. Choose `Window > Show View > Other...`
   11. Select `Java > Package Explorer` (I suggest replacing Project Explorer with this)
   12. Expand `cs320` in the `Package Explorer`
   13. Right-click on `src/test/java`, choose `Run As > JUnit Test`
   14. **If the sample test passes, you're good to go**

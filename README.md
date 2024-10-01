<p align="center">
  <img src="https://raw.githubusercontent.com/PKief/vscode-material-icon-theme/ec559a9f6bfd399b82bb44393651661b08aaf7ba/icons/folder-markdown-open.svg" width="100" alt="project-logo">
</p>
<p align="center">
    <h1 align="center">TRYEXCEPTPYCHARMPLUGIN</h1>
</p>
<p align="center">
    <em>Empower Python code with effortless error handling!</em>
</p>
<p align="center">
	<img src="https://img.shields.io/github/license/AarchiveSoft/TryExceptPyCharmPlugin.git?style=default&logo=opensourceinitiative&logoColor=white&color=0080ff" alt="license">
	<img src="https://img.shields.io/github/last-commit/AarchiveSoft/TryExceptPyCharmPlugin.git?style=default&logo=git&logoColor=white&color=0080ff" alt="last-commit">
	<img src="https://img.shields.io/github/languages/top/AarchiveSoft/TryExceptPyCharmPlugin.git?style=default&color=0080ff" alt="repo-top-language">
	<img src="https://img.shields.io/github/languages/count/AarchiveSoft/TryExceptPyCharmPlugin.git?style=default&color=0080ff" alt="repo-language-count">
<p>
<p align="center">
	<!-- default option, no dependency badges. -->
</p>

<br><!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary><br>

- [ Overview](#-overview)
- [ Features](#-features)
- [ Repository Structure](#-repository-structure)
- [ Modules](#-modules)
- [ Getting Started](#-getting-started)
  - [ Installation](#-installation)
  - [ Usage](#-usage)
  - [ Tests](#-tests)
- [ Project Roadmap](#-project-roadmap)
- [ Contributing](#-contributing)
- [ License](#-license)
- [ Acknowledgments](#-acknowledgments)
</details>
<hr>

##  Overview

TryExceptPyCharmPlugin is an IntelliJ plugin facilitating Python development by streamlining try-except block management. It empowers developers to effortlessly insert and remove try-except blocks in Python files within JetBrains IDEs. The projects core functionalities center around enhancing exception handling in Python code, ensuring proper indentation and code formatting. By automating these tasks, TryExceptPyCharmPlugin boosts productivity and maintains coding standards, offering significant value to Python developers within the IntelliJ environment.

---

##  Features

|    | Feature          | Description |
|----|------------------|-------------------------------------------------------|
| ⚙️  | **Architecture** | The project leverages a Gradle build system for managing dependencies and resources, integrating Kotlin and Java. It implements specific actions for adding and removing try-except blocks in Python files within JetBrains IDEs. |
| 🔩 | **Code Quality** | The codebase follows Java and Kotlin coding standards, utilizing Gradle for build automation. The project enforces proper indentation and code reformatting in Python files for consistent style. |
| 📄 | **Documentation** | The project provides detailed documentation for setting up the plugin, configuring build settings, managing dependencies, and understanding the implemented actions for try-except blocks manipulation. |
| 🔌 | **Integrations** | Key integrations include JetBrains IDEs, Kotlin, Gradle, and Maven Central for managing dependencies. The project tightly integrates with IntelliJ platform for enhancing Python development workflow. |
| 🧩 | **Modularity** | The codebase exhibits modularity through distinct actions for adding and removing try-except blocks, facilitating code reusability and easy maintenance. Each action is self-contained, enhancing the overall modularity of the plugin. |
| 🧪 | **Testing** | Testing frameworks and tools used for the project are not explicitly mentioned in the provided details. Further insights into testing approaches would be beneficial for evaluating the test coverage and robustness of the plugin. |
| ⚡️  | **Performance** | The plugin focuses on enhancing developer productivity by efficiently adding and removing try-except blocks in Python files within JetBrains IDEs. The performance is optimized for quick and accurate code modifications. |
| 🛡️ | **Security** | The project emphasizes data protection by focusing on accurate exception handling within Python files, ensuring code integrity during try-except block manipulations. Access controls are enforced through the JetBrains IDE environment. |
| 📦 | **Dependencies** | Key external libraries and dependencies include JetBrains IDE platform, Kotlin, Gradle, and Maven Central for managing plugin resources and integration with the IntelliJ ecosystem. |
| 🚀 | **Scalability** | The project's architecture and design allow for scalability to handle increased demand for adding and removing try-except blocks in Python files, accommodating diverse workflows and project sizes within JetBrains IDEs. |

---

##  Repository Structure

```sh
└── TryExceptPyCharmPlugin/
    ├── build.gradle.kts
    ├── gradle
    │   └── wrapper
    ├── gradle.properties
    ├── gradlew
    ├── gradlew.bat
    ├── libs
    │   ├── python-ast-241.18034.82-sources.jar
    │   ├── python-ast-241.18034.82.jar
    │   ├── python-community-impl-241.18034.82.jar
    │   ├── python-parser-241.18034.82.jar
    │   ├── python-psi-241.18034.82.jar
    │   ├── python-sdk-241.18034.82.jar
    │   └── python-syntax-core-241.18034.82.jar
    ├── settings.gradle.kts
    └── src
        └── main
```

---

##  Modules

<details closed><summary>.</summary>

| File                                                                                                              | Summary                                                                                                                                                                                                             |
| ---                                                                                                               | ---                                                                                                                                                                                                                 |
| [build.gradle.kts](https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git/blob/master/build.gradle.kts)       | Configures IntelliJ plugin build settings, defining dependencies, target versions, and resource handling. It integrates Kotlin and Java for development and defines plugin metadata like version and compatibility. |
| [gradlew.bat](https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git/blob/master/gradlew.bat)                 | Executes Gradle build commands, manages JVM options, and sets up the environment for Gradle in Windows. Resolves Java path, sets classpath, and initializes Gradle. Ensures compliance with the Apache License.     |
| [settings.gradle.kts](https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git/blob/master/settings.gradle.kts) | Manages plugin dependencies via Maven Central and Gradle Plugin Portal.-Sets root project name to try_except_plugin_v3.-Key role in managing external dependencies for the parent repository.                       |

</details>

<details closed><summary>src.main.kotlin.com.softwaare.try_except_plugin_v3</summary>

| File                                                                                                                                                                           | Summary                                                                                                                                                                                                                                             |
| ---                                                                                                                                                                            | ---                                                                                                                                                                                                                                                 |
| [AddTryExceptAction.kt](https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git/blob/master/src\main\kotlin\com\softwaare\try_except_plugin_v3\AddTryExceptAction.kt)       | Implements AddTryExceptAction to insert a try-except block in Python files with proper indentation and code reformatting. Handles project, editor, and file data to ensure accurate exception handling.                                             |
| [RemoveTryExceptAction.kt](https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git/blob/master/src\main\kotlin\com\softwaare\try_except_plugin_v3\RemoveTryExceptAction.kt) | Implements removing try-except blocks in Python files from a JetBrains IDE. Identifies Python file type, locates try-except statements, accesses try block, and removes it with proper indentation. Reformatting is applied to maintain code style. |

</details>

---

##  Getting Started

**System Requirements:**

* **Kotlin**: `version x.y.z`

###  Installation

<h4>From <code>source</code></h4>

> 1. Clone the TryExceptPyCharmPlugin repository:
>
> ```console
> $ git clone https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git
> ```
>
> 2. Change to the project directory:
> ```console
> $ cd TryExceptPyCharmPlugin
> ```
>
> 3. Install the dependencies:
> ```console
> $ gradle build
> ```

###  Usage

<h4>From <code>source</code></h4>

> Run TryExceptPyCharmPlugin using the command below:
> ```console
> $ java -jar build/libs/myapp.jar
> ```

###  Tests

> Run the test suite using the command below:
> ```console
> $ gradle test
> ```

---

##  Project Roadmap

- [X] `► INSERT-TASK-1`
- [ ] `► INSERT-TASK-2`
- [ ] `► ...`

---

##  Contributing

Contributions are welcome! Here are several ways you can contribute:

- **[Report Issues](https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git/issues)**: Submit bugs found or log feature requests for the `TryExceptPyCharmPlugin` project.
- **[Submit Pull Requests](https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git/blob/main/CONTRIBUTING.md)**: Review open PRs, and submit your own PRs.
- **[Join the Discussions](https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git/discussions)**: Share your insights, provide feedback, or ask questions.

<details closed>
<summary>Contributing Guidelines</summary>

1. **Fork the Repository**: Start by forking the project repository to your github account.
2. **Clone Locally**: Clone the forked repository to your local machine using a git client.
   ```sh
   git clone https://github.com/AarchiveSoft/TryExceptPyCharmPlugin.git
   ```
3. **Create a New Branch**: Always work on a new branch, giving it a descriptive name.
   ```sh
   git checkout -b new-feature-x
   ```
4. **Make Your Changes**: Develop and test your changes locally.
5. **Commit Your Changes**: Commit with a clear message describing your updates.
   ```sh
   git commit -m 'Implemented new feature x.'
   ```
6. **Push to github**: Push the changes to your forked repository.
   ```sh
   git push origin new-feature-x
   ```
7. **Submit a Pull Request**: Create a PR against the original project repository. Clearly describe the changes and their motivations.
8. **Review**: Once your PR is reviewed and approved, it will be merged into the main branch. Congratulations on your contribution!
</details>

<details closed>
<summary>Contributor Graph</summary>
<br>
<p align="center">
   <a href="https://github.com{/AarchiveSoft/TryExceptPyCharmPlugin.git/}graphs/contributors">
      <img src="https://contrib.rocks/image?repo=AarchiveSoft/TryExceptPyCharmPlugin.git">
   </a>
</p>
</details>

---

##  License

This project is protected under the [SELECT-A-LICENSE](https://choosealicense.com/licenses) License. For more details, refer to the [LICENSE](https://choosealicense.com/licenses/) file.

---

##  Acknowledgments

- List any resources, contributors, inspiration, etc. here.

[**Return**](#-overview)

---

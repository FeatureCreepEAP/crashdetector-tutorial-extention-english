# CrashDetector Extensions

CrashDetector extensions are normal Java JAR files that add features to CrashDetector.

An extension can register new checks, GUI panels, buttons, log destinations, APIs, language overrides, app-side hooks, or other CrashDetector integrations.

The extension system is intentionally simple. A CrashDetector extension is just:

1. A normal `.jar` file
2. A class that implements `com.asbestosstar.crashdetector.Extencion`
3. A manifest entry named `ExtencionCrashDetector` that points to that class

Example manifest entry:

```text
ExtencionCrashDetector: com.asbestosstar.crashdetector_tutorial_extention_english.TutorialExtention
```

---

## Requirements

Your extension needs CrashDetector on the compile classpath.

Current Maven artifact:

```text
com.asbestosstar:crashdetector:0.9.4
```

Maven Central location:

```text
https://repo1.maven.org/maven2/com/asbestosstar/crashdetector/0.9.4/
```

Use your normal build system to add that dependency. Gradle, Maven, and other Java build tools are fine.

The CrashDetector dependency should normally be compile-only/provided. Do not bundle CrashDetector inside your extension JAR.

---

## Basic Extension Class

Create a class that implements `Extencion`.

```java
package com.asbestosstar.crashdetector_tutorial_extention_english;

import com.asbestosstar.crashdetector.CrashDetectorLogger;
import com.asbestosstar.crashdetector.Extencion;

public class TutorialExtention implements Extencion {

    @Override
    public void procesoDelApp() {
        CrashDetectorLogger.log("[TutorialExtention] Loaded in app process");
    }

    @Override
    public void procesoDeLaMonitorizacionDePID() {
        CrashDetectorLogger.log("[TutorialExtention] Loaded in monitor process");
    }
}
```

The class name in the manifest must be the full package and class name:

```text
com.asbestosstar.crashdetector_tutorial_extention_english.TutorialExtention
```

---

## Extension Lifecycle

CrashDetector has two important processes.

### `procesoDelApp()`

This method runs inside the application/game process.

Use this method for app-side features, such as:

- patches
- transformers
- prelaunch hooks
- app/game process setup
- loader-related integrations

This is similar in purpose to systems like:

- Java agents
- FeatureCreep agents
- ModLauncher `ITransformationService`
- Fabric Loader `PreLaunchEntrypoint`

Not every loader supports the same kind of transformation system. For example, Fabric by itself does not provide the same transformation model as ModLauncher unless something like SpongeMixin or FeatureCreep is involved.

### `procesoDeLaMonitorizacionDePID()`

This method runs inside the CrashDetector monitor process.

Use this method for CrashDetector-side features, such as:

- registering verifications
- registering GUI panels
- adding sidebar buttons
- adding APIs
- adding log destinations
- registering language overrides
- adding report or analysis tools

This process is separate from the app/game process. Do not assume that objects from the app process are available here.

---

## Required Manifest Entry

Your JAR must contain this manifest entry:

```text
ExtencionCrashDetector: your.full.ExtensionClassName
```

Example:

```text
ExtencionCrashDetector: com.asbestosstar.crashdetector_tutorial_extention_english.TutorialExtention
```

CrashDetector reads this exact manifest key. If the entry is missing or misspelled, the JAR will not be loaded as an extension.

Correct:

```text
ExtencionCrashDetector
```

Incorrect:

```text
ExtensionCrashDetector
CrashDetectorExtension
Extencion
```

---

## JAR File

The output should be a normal `.jar` file.

CrashDetector extensions do not need a special file format. If the manifest entry is present, CrashDetector can detect the JAR as an extension.

The extension file may use one of the supported extension file endings recognized by CrashDetector, but the normal and recommended output is simply:

```text
your-extension-name.jar
```

---

## Package Naming

Use valid Java package names.

Good:

```text
com.asbestosstar.crashdetector_tutorial_extention_english
```

Bad:

```text
com.asbestosstar.crashdetector-tutorial-extention-english
```

Hyphens are not valid in Java package names. Use underscores if needed.

---

## Installing the Extension

Build the extension JAR and place it somewhere CrashDetector will scan as part of the mod list.

When CrashDetector sees the JAR, it checks the manifest. If it finds `ExtencionCrashDetector`, it loads the class and calls the appropriate method for the current process.

---

## Testing the Extension

A simple first test is to log from both methods:

```java
@Override
public void procesoDelApp() {
    CrashDetectorLogger.log("[MyExtension] app process loaded");
}

@Override
public void procesoDeLaMonitorizacionDePID() {
    CrashDetectorLogger.log("[MyExtension] monitor process loaded");
}
```

Then run CrashDetector and check the logs.

Depending on the environment, you may see one or both messages. The app process and monitor process load at different times.

---

## Common Mistakes

### Missing manifest entry

If the JAR does not contain `ExtencionCrashDetector`, CrashDetector will ignore it.

### Wrong class name in the manifest

This will not work:

```text
ExtencionCrashDetector: TutorialExtention
```

Use the full class name:

```text
ExtencionCrashDetector: com.asbestosstar.crashdetector_tutorial_extention_english.TutorialExtention
```

### Class does not implement `Extencion`

The target class must implement:

```java
com.asbestosstar.crashdetector.Extencion
```

### Bundling CrashDetector inside the extension

The extension should normally compile against CrashDetector but not include CrashDetector inside its own JAR.

Use the equivalent of `compileOnly` or `provided`, depending on your build system.

### Assuming both processes share state

`procesoDelApp()` and `procesoDeLaMonitorizacionDePID()` run in different processes.

Do not rely on static fields, singleton instances, or in-memory objects being shared between them.

### Expecting all loaders to support the same patching behavior

Some app-side hooks may work differently depending on the launcher, loader, or Java environment.

Monitor-side features are usually easier to make portable.

---

## Minimal Checklist

Before testing, confirm:

- The project compiles against `com.asbestosstar:crashdetector:0.9.4
`
- The extension class implements `Extencion`
- The extension class has a public no-argument constructor
- The JAR manifest contains `ExtencionCrashDetector`
- The manifest value is the full class name
- CrashDetector is able to see the JAR in its scanned mod list

---

## Example

Manifest:

```text
ExtencionCrashDetector: com.asbestosstar.crashdetector_tutorial_extention_english.TutorialExtention
```

Class:

```java
package com.asbestosstar.crashdetector_tutorial_extention_english;

import com.asbestosstar.crashdetector.CrashDetectorLogger;
import com.asbestosstar.crashdetector.Extencion;

public class TutorialExtention implements Extencion {

    @Override
    public void procesoDelApp() {
        CrashDetectorLogger.log("Tutorial extension loaded in app process");
    }

    @Override
    public void procesoDeLaMonitorizacionDePID() {
        CrashDetectorLogger.log("Tutorial extension loaded in monitor process");
    }
}
```

That is enough for a basic CrashDetector extension.

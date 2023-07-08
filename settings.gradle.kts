pluginManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        gradlePluginPortal()
    }
}

rootProject.name = "Compose Custom Tab"

include(":demo-material2")
include(":demo-material3")
include(":core")
include(":customtabs")
include(":customtabs-material3")
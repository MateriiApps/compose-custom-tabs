import com.android.build.gradle.internal.ide.kmp.KotlinAndroidSourceSetMarker.Companion.android
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType

@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.compose)
    id("maven-publish")
    id("signing")
}

android {
    namespace = "dev.materii.composecustomtabs"
    compileSdk = 33

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")

    defaultConfig {
        minSdk = 21
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(17)
    }

    publishing {
        singleVariant("release") {
            withSourcesJar()
        }
    }
}

task<Jar>("emptyJavadocJar") {
    archiveClassifier.set("javadoc")
}

val sonatypeUsername: String? = System.getenv("SONATYPE_USERNAME")
val sonatypePassword: String? = System.getenv("SONATYPE_PASSWORD")

publishing {
    repositories {
        if (sonatypeUsername == null || sonatypePassword == null)
            mavenLocal()
        else {
            maven {
                credentials {
                    username = sonatypeUsername
                    password = sonatypePassword
                }
                setUrl("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            }
        }
    }

    publications.withType<MavenPublication> {
        artifact(tasks["emptyJavadocJar"])

        pom {
            name.set("compose-custom-tabs")
            description.set("Custom tabs implemented in Jetpack Compose")
            url.set("https://github.com/MateriiApps/compose-custom-tabs")
            licenses {
                license {
                    name.set("MIT License")
                    url.set("https://opensource.org/license/mit/")
                }
            }
            developers {
                developer {
                    id.set("wingio")
                    name.set("Wing")
                    url.set("https://wingio.xyz")
                }
            }
            scm {
                url.set("https://github.com/MateriiApps/compose-custom-tabs")
                connection.set("scm:git:github.com/MateriiApps/compose-custom-tabs.git")
                developerConnection.set("scm:git:ssh://github.com/MateriiApps/compose-custom-tabs.git")
            }
        }
    }
}

if (sonatypeUsername != null && sonatypePassword != null) {
    signing {
        useInMemoryPgpKeys(
            System.getenv("SIGNING_KEY_ID"),
            System.getenv("SIGNING_KEY"),
            System.getenv("SIGNING_PASSWORD"),
        )
        sign(publishing.publications)
    }


    // See https://youtrack.jetbrains.com/issue/KT-46466/Kotlin-MPP-publishing-Gradle-7-disables-optimizations-because-of-task-dependencies#focus=Comments-27-7102038.0-0
    val dependsOnTasks = mutableListOf<String>()
    tasks.withType<AbstractPublishToMaven>().configureEach {
        dependsOnTasks.add(name.replace("publish", "sign").replaceAfter("Publication", ""))
        dependsOn(dependsOnTasks)
    }
}

kotlin {
    android {
        publishLibraryVariants("release")
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(project(":core"))
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.foundation)
                implementation(compose.material)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(libs.accompanist.webview)
            }
        }
    }
}
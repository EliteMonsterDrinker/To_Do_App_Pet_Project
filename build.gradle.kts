plugins {
    application
    java
}

dependencies{
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher:1.10.0")
}

java{
    toolchain{
        languageVersion.set(JavaLanguageVersion.of(27))
    }
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("com.app.Activator")
}

tasks.run.configure{
    environment("_JAVA_AWT_WM_NONREPARENTING", "1")
}

tasks.test{
    useJUnitPlatform()
}


plugins {
    application
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

//tasks.test {
    //useJUnitPlatform()
//}

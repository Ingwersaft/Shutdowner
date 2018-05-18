import com.github.alexeylisyutenko.windowsserviceplugin.Startup
import sun.tools.jar.resources.jar

plugins {
    kotlin("jvm") version "1.2.31"
    id("com.github.alexeylisyutenko.windows-service-plugin") version "1.1.0"
}

group = "com.mkring.shutdowner"

dependencies {
    compile(kotlin("stdlib"))
    compile("io.javalin:javalin:1.6.1")
    compile("ch.qos.logback:logback-classic:1.2.3")
}

repositories {
    jcenter()
}

tasks.withType<Jar> {
    manifest.attributes["Main-Class"] = "com.mkring.shutdowner.ShutdownerKt"
    configurations["compileClasspath"].forEach { file: File ->
        from(zipTree(file.absoluteFile))
    }
}
windowsService {
    displayName = "Shutdowner service"
    description = "Service generated with using gradle plugin"
    startClass = "com.mkring.shutdowner.ShutdownerKt"
    startMethod = "main"
    startParams = "start"
    stopClass = "com.mkring.shutdowner.ShutdownerKt"
    stopMethod = "main"
    stopParams = "stop"
    startup = Startup.AUTO
    overridingClasspath = files("build/libs/Shutdowner.jar")
}
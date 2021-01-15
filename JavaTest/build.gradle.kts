plugins {
    //Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/jcenter")
}

dependencies {
//    implementation fileTree(include: ['*.jar'], dir: 'libs')

    // About JUnit
    testImplementation("junit:junit:4.13")
    implementation("com.google.guava:guava:29.0-jre")

    // About RxJava
    implementation("io.reactivex.rxjava2:rxjava:2.2.2")

    // About Json
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("org.json:json:20160212")

    implementation("commons-codec:commons-codec:1.10")
    implementation("org.apache.commons:commons-lang3:3.4")
}

application {
//    mainClass.set("com.hello.Test_1")
}
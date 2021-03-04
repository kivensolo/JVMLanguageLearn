plugins {
    maven
    kotlin("jvm") version "1.3.61"
}

repositories {
    maven("https://maven.aliyun.com/repository/public")
    
    maven("https://maven.aliyun.com/repository/google")
}


val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

// Gradle 默认原代码集是 src/main/java 自定义代码目录的话，需要修改sourceSets
sourceSets{
    main{
        java{
            //自定义打包源码目录
            srcDirs("src/main/kotlin")
        }
    }
}

//uploadArchives {
//    repositories {
//        mavenDeployer {
//            //提交到远程服务器：
//            // repository(url: "http://www.xxx.com/repos") {
//            //    authentication(userName: "admin", password: "admin")
//            // }
//            //本地的Maven地址设置为D:/repos
//            repository(url: uri('D:/repos'))
//
//            flatDir {
//                dirs 'repos'
//            }
//        }
//    }
//}

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
    implementation(kotlin("stdlib-jdk8"))
}

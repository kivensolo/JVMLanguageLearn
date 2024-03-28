plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    kotlin("jvm") version "1.3.61"

    //	Apply the java-library plugin for API and implementation separation.
    `java-library`

    // application插件用于创建可执行的JVM应用程序
//    application
}

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/jcenter")
}

// Gradle 默认会从 src/main/java 搜寻打包源码，在 src/test/java 下搜寻测试源码
// https://blog.csdn.net/wopelo/article/details/106136697
//sourceSets{
////     groovy DSL  main.java.srcDirs("src/main/kotlin")
//    main{
//        java{
//            //自定义打包源码目录
//            srcDirs("src/main/kotlin")
//        }
//    }
//}


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
     // This dependency is used by the application.
    implementation("com.google.guava:guava:29.0-jre")

    // Kotlin标准库
    implementation(kotlin("stdlib"))
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.61")
    // Kotlin反射库
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.3.61")
    // Kotlin协程核心库
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.2")

    // Align versions of all Kotlin components
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    // Use the Kotlin JDK 8 standard library.    compile(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")


        // About JUnit
    implementation("junit:junit:4.13")
    testImplementation("junit:junit:4.13")

     // About RxJava
    implementation("io.reactivex.rxjava2:rxjava:2.2.2")

    // About Json
    implementation("com.google.code.gson:gson:2.8.5")
    implementation("org.json:json:20160212")

    implementation("commons-codec:commons-codec:1.10")
    implementation("org.apache.commons:commons-lang3:3.4")


    //SAM4
    implementation("org.ow2.asm:asm:4.0")
}
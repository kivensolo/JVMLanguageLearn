plugins {
    //Apply the application plugin to add support for building a CLI application in Java.
    application
}

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/jcenter")
}

// Gradle 默认会从 src/main/java 搜寻打包源码，在 src/test/java 下搜寻测试源码
//sourceSets{
//    main{
//        java{
//            //自定义打包源码目录
//            srcDirs("src/main/kotlin")
//        }
//    }
//}

dependencies {
//    implementation fileTree(include: ['*.jar'], dir: 'libs')

    // About JUnit
    testImplementation("junit:junit:4.13")
}
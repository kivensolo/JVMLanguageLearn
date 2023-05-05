plugins {
    //	Apply the java-library plugin for API and implementation separation.
    `java-library`
}

version '1'

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/jcenter")
}

dependencies {
    //SAM9
    implementation("org.ow2.asm:asm:9.0")
}

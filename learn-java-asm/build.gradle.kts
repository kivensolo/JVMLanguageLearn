plugins {
    //	Apply the java-library plugin for API and implementation separation.
    `java-library`
}

version '1'

extra["asm-version"] = "9.0"

repositories {
    maven("http://maven.aliyun.com/nexus/content/groups/public/")
    maven("https://maven.aliyun.com/repository/google")
    maven("https://maven.aliyun.com/repository/jcenter")
}

dependencies {
    //SAM9 implementation("org.ow2.asm:asm:{${ext.get("asm-version")}}")
    implementation("org.ow2.asm:asm-commons:9.0")
    implementation("org.ow2.asm:asm-util:9.0")
    implementation("org.ow2.asm:asm-tree:9.0")
    implementation("org.ow2.asm:asm-analysis:9.0")
}

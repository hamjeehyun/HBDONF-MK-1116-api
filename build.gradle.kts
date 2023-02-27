plugins {
    id("org.springframework.boot") version "2.2.6.RELEASE"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
    id ("java")
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    kotlin("plugin.jpa") version "1.6.21"
}
group = "com.devfuse.mk"
version = "0.0.1-SNAPSHOT"
description = "HBDONF-MK-1116-api"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencyManagement {
    imports {
        mavenBom ("org.springframework.cloud:spring-cloud-dependencies:Hoxton.SR12")
    }
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation ("org.springframework.boot:spring-boot-starter")
    implementation ("org.springframework.boot:spring-boot-starter-web")
    implementation ("org.springframework.security:spring-security-web")
    implementation("org.springframework.boot:spring-boot-starter-security:2.5.4")

    implementation ("com.google.firebase:firebase-admin:8.0.1")

    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    runtimeOnly ("org.mariadb.jdbc:mariadb-java-client")

    implementation("io.netty:netty-resolver-dns-native-macos:4.1.68.Final:osx-aarch_64")

    // swagger
    implementation ("io.springfox:springfox-swagger2:2.9.2")
    implementation ("io.springfox:springfox-swagger-ui:2.9.2")
    implementation ("com.googlecode.json-simple:json-simple:1.1.1")

    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")

    implementation("com.google.firebase:firebase-admin:8.0.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}



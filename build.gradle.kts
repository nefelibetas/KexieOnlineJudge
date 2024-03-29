import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id ("org.springframework.boot") version "3.1.6"
    id ("io.spring.dependency-management") version "1.1.4"
    kotlin("jvm") version "1.9.20"
    kotlin("plugin.spring") version "1.9.20"
    kotlin("kapt") version "1.9.0"
}

group = "com.fish"
version = "0.0.1"
description = "KexieOnlineJudge"
java {
    sourceCompatibility = JavaVersion.VERSION_17
}

ext {
    extra["snakeyaml.version"]  = "2.2"
}
val springCloudVersion by extra("2022.0.5")
val springBootVersion by extra("3.1.6")

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-json")
    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-api:2.3.0")
    implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.3.0")
    // 其他库
    implementation("com.zaxxer:HikariCP")
    implementation("io.jsonwebtoken:jjwt:0.12.3")
    implementation("javax.xml.bind:jaxb-api:2.4.0-b180830.0359")
    implementation("com.mybatis-flex:mybatis-flex-spring-boot-starter:1.8.2")
    implementation("com.mybatis-flex:mybatis-flex-kotlin-extensions:1.0.5")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.22")
    implementation("org.springframework.cloud:spring-cloud-starter-openfeign")
    // runtimeOnly
    runtimeOnly("com.mysql:mysql-connector-j")
    // annotation
    annotationProcessor("org.projectlombok:lombok")
    // test
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
    // kapt
    kapt("com.mybatis-flex:mybatis-flex-processor:1.7.3")
}
dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:$springCloudVersion")
        mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.bootBuildImage {
    builder.set("paketobuildpacks/builder-jammy-base:latest")
}



plugins {
    id("java-library")
    id("maven-publish")
    id("org.springframework.boot") version "4.0.5"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "fun.imiku"
version = "0.0.1-SNAPSHOT"

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    mavenCentral()
}

dependencies {
	api(platform("org.springframework.boot:spring-boot-dependencies:4.0.5"))
    api("org.springframework.boot:spring-boot-autoconfigure")
    api("org.springframework:spring-context")
    api("org.springframework.boot:spring-boot-starter-websocket")

    implementation("org.slf4j:slf4j-api")
    implementation("tools.jackson.core:jackson-databind")
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")

    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
}

java {
    withSourcesJar()
    withJavadocJar()
    sourceCompatibility = JavaVersion.VERSION_25
    targetCompatibility = JavaVersion.VERSION_25
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}

tasks {
    withType<Test> {
        useJUnitPlatform()
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<Javadoc> {
        val opts = options as StandardJavadocDocletOptions
        opts.encoding = "UTF-8"
        opts.addBooleanOption("Xdoclint:none", true)
    }

    named<Jar>("jar") {
        // Remove `plain` postfix from jar file name
        archiveClassifier = ""
        enabled = true
    }

    named("bootJar") {
        enabled = false
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
            artifactId = "napcat4j-spring-boot-starter"
        }
    }
}

subprojects {
    tasks.withType<JavaCompile> {
        options.release.set(25)
    }

    tasks.withType<Test> {
        useJUnitPlatform()
    }
}

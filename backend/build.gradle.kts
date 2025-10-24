plugins {
   application
   id("org.springframework.boot") version "3.3.5"
   id("io.spring.dependency-management") version "1.1.6"
}

group = "org.example"
version = "1.0-SNAPSHOT"
java.toolchain.languageVersion = JavaLanguageVersion.of(21)

tasks.getByName<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
   archiveFileName.set("app.jar")
}

repositories {
   mavenCentral()
}

dependencies {
   implementation("org.springframework.boot:spring-boot-starter-web")
   implementation("org.jsoup:jsoup:1.21.2")
   testImplementation(platform("org.junit:junit-bom:5.10.0"))
   testImplementation("org.junit.jupiter:junit-jupiter")
   implementation("org.springframework.boot:spring-boot-starter-web")
   implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
   developmentOnly("org.springframework.boot:spring-boot-devtools")
   testImplementation("org.springframework.boot:spring-boot-starter-test")
   compileOnly("org.projectlombok:lombok:1.18.32")
   annotationProcessor("org.projectlombok:lombok:1.18.32")
   implementation("com.google.genai:google-genai:1.24.0")

}

tasks.test {
   useJUnitPlatform()
}
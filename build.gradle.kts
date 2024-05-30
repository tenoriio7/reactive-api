import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val pactVersion = "4.4.9"


plugins {
	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"
	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
}

group = "br.com.tenorio"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0")
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("org.springframework.boot:spring-boot-starter-websocket")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation ("com.google.code.gson:gson:2.8.8")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
	implementation("org.springframework.kafka:spring-kafka") // Use a versão mais recente compatível com o Spring Boot
	compileOnly("org.projectlombok:lombok")
	runtimeOnly("com.h2database:h2")
	annotationProcessor("org.projectlombok:lombok")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.boot:spring-boot-testcontainers")
	testImplementation("io.projectreactor:reactor-test")
	testImplementation("org.testcontainers:junit-jupiter")
	implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
	implementation("io.r2dbc:r2dbc-h2")
	implementation ("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("de.codecentric:chaos-monkey-spring-boot:3.1.0")
	implementation ("org.springframework.boot:spring-boot-starter-actuator")
	implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")

	testImplementation("org.springframework.kafka:spring-kafka-test")

	implementation("au.com.dius.pact.consumer:junit5:4.4.9")
	implementation("au.com.dius.pact.provider:junit:4.4.9")
	implementation("au.com.dius.pact.provider:junit5:4.4.9")
	implementation("commons-codec:commons-codec:1.15")
	testImplementation("org.mockito:mockito-core:3.11.2")


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

plugins {
    java
    eclipse
}

allprojects {
    group = "it.discovery"
}

subprojects {
    apply(plugin = "java")

    java.sourceCompatibility = JavaVersion.VERSION_18
    java.targetCompatibility = JavaVersion.VERSION_18

    repositories {
        mavenCentral()
        maven { url = uri("https://repo.spring.io/milestone") }
        maven { url = uri("https://repo.spring.io/snapshot") }
    }

    dependencies {
        val hibernateVersion: String by project
        implementation("org.hibernate.orm:hibernate-core:$hibernateVersion")
        runtimeOnly("com.h2database:h2:2.1.214")
        runtimeOnly("mysql:mysql-connector-java:8.0.30")
        runtimeOnly("org.postgresql:postgresql:42.4.1")

        compileOnly("org.projectlombok:lombok:1.18.24")
        annotationProcessor("org.projectlombok:lombok:1.18.24")
    }
}


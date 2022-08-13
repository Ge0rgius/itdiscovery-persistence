dependencies {
    val springVersion: String by project
    val hibernateVersion: String by project

    implementation("org.springframework:spring-context:$springVersion")
    implementation("org.springframework:spring-orm:$springVersion")

    implementation("org.apache.logging.log4j:log4j-core:2.18.0")
}

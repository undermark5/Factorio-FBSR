/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    maven {
        url = uri("https://jcenter.bintray.com")
    }

    maven {
        url = uri("https://repo.maven.apache.org/maven2/")
    }
}

dependencies {
    api(libs.com.google.guava.guava)
    api(libs.com.demod.factorio.factoriodatawrapper)
    api(libs.commons.codec.commons.codec)
    api(libs.net.sf.jopt.simple.jopt.simple)
    api(libs.com.demod.dcba.discordcorebotapple)
    api(libs.org.apache.commons.commons.text)
    api(libs.org.apache.httpcomponents.fluent.hc)
    api(libs.net.dean.jraw.jraw)
    api(libs.org.rapidoid.rapidoid.web)
    api(libs.org.slf4j.slf4j.api)
    api(libs.com.squareup.okhttp3.okhttp)
    api(libs.org.apache.commons.commons.io)
    api(libs.javax.xml.bind.jaxb.api)
    api(libs.org.dizitart.nitrite)
}

group = "com.demod.fbsr"
version = "0.0.1-SNAPSHOT"
description = "FactorioBlueprintStringRenderer"
java.sourceCompatibility = JavaVersion.VERSION_1_8

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

package com.ttest.jmst

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
open class JmstApplication

fun main(args: Array<String>) {
    SpringApplication.run(JmstApplication::class.java, *args)
}

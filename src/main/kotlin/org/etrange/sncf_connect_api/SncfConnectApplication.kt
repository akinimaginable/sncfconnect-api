package org.etrange.sncf_connect_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SncfConnectApplication

fun main(args: Array<String>) {
    runApplication<SncfConnectApplication>(*args)
}

package com.mkring.shutdowner

import io.javalin.Javalin

fun main(args: Array<String>) {
    val app = Javalin.start(28828)
    app.get("/") {
        Runtime.getRuntime().exec("shutdown -s -t 10")
        it.result("shutdown triggered")
    }
}


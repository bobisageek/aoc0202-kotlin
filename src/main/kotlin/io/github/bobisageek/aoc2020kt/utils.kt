package io.github.bobisageek.aoc2020kt

fun dayLines(fileName: String) = "$fileName.txt".let { ClassLoader.getSystemResource(it) }
    .readText().lines().dropLastWhile { it.isBlank() }

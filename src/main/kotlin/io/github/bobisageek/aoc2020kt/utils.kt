package io.github.bobisageek.aoc2020kt

fun dayLines(fileName: String) = "$fileName.txt".let { ClassLoader.getSystemResource(it) }
    .readText().lines().dropLastWhile { it.isBlank() }

fun Iterable<CharSequence>.regexExtract(rgx: Regex) = this.map { rgx.matchEntire(it)?.groupValues?.drop(1) }

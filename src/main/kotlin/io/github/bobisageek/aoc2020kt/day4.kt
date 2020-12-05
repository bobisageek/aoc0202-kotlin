package io.github.bobisageek.aoc2020kt

typealias Day4Passports = List<Map<String, String>>

fun main() {
    val input = dayText("4")
    val ps = Day4.passports(input)
    println(Day4.part1(ps))
    println(Day4.part2(ps))
}

object Day4 {
    fun passports(input: String) = input.split(Regex("(\\r?\\n)\\1"))
        .map {
            it.split(Regex(":|\\s+"))
                .windowed(2, 2)
                .map { (f, s) -> f to s }
                .toMap()
        }

    fun part1(input: Day4Passports): Int {
        val requiredKeys = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
        return input.count { p -> requiredKeys.all { p.containsKey(it) } }
    }

    fun part2(input: Day4Passports): Int {
        fun yearInRange(min: Int, max: Int, y: String) =
            y.matches(Regex("[0-9]{4}")) && y.toInt() in min..max

        fun checkHeight(h: String): Boolean = Regex("(?:(1[5-9]\\d)cm|([5-7]\\d)in)").matchEntire(h)?.let {
            val (cm, inches) = it.groupValues.drop(1)
            if (cm.isNotBlank()) cm.toInt() in (150..193) else inches.toInt() in (59..76)
        } ?: false

        val validations = mapOf<String, (String) -> Boolean>(
            "byr" to { yearInRange(1920, 2002, it) },
            "iyr" to { yearInRange(2010, 2020, it) },
            "eyr" to { yearInRange(2020, 2030, it) },
            "hgt" to { checkHeight(it) },
            "hcl" to { Regex("#[0-9a-f]{6}").matches(it) },
            "ecl" to { it in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
            "pid" to { Regex("[0-9]{9}").matches(it) }
        )
        return input.count { p -> validations.all { (k, f) -> f(p.getOrDefault(k, "")) } }
    }

}

package io.github.bobisageek.aoc2020kt

fun main() {
    val program: List<Pair<String, Int>> = dayLines("8").map {
        it.split(' ').let { (op, num) -> op to num.toInt() }
    }
    println(Day8.part1(program))
    println(Day8.part2(program))
}

object Day8 {
    fun part1(program: List<Pair<String, Int>>): Pair<String, Int> {
        tailrec fun exec(pos: Int, acc: Int, seen: Set<Int>): Pair<String, Int> {
            return if (program.size <= pos) "halt" to acc else {
                val (op, num) = program[pos]
                when {
                    seen.contains(pos) -> "loop" to acc
                    op == "nop" -> exec(pos + 1, acc, seen + pos)
                    op == "jmp" -> exec(pos + num, acc, seen + pos)
                    op == "acc" -> exec(pos + 1, acc + num, seen + pos)
                    else -> throw Exception("Panic")
                }
            }
        }
        return exec(0, 0, emptySet())
    }

    fun part2(program: List<Pair<String, Int>>): Int {
        val subs = mapOf("jmp" to "nop", "nop" to "jmp")
        fun <T> List<T>.withReplacements(replacements: Map<Int, T>) =
            this.mapIndexed { index, t -> replacements.getOrDefault(index, t) }

        tailrec fun findHalt(pos: Int): Int {
            val (ins, num) = program[pos]
            val newIns = subs[ins]
            return if (newIns == null)
                findHalt(pos + 1)
            else {
                val outcome = part1(program.withReplacements(mapOf(pos to (newIns to num))))
                when (outcome.first) {
                    "loop" -> findHalt(pos + 1)
                    "halt" -> outcome.second
                    else -> throw Exception("panic")
                }
            }
        }
        return findHalt(0)
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        var gammaRates = ""
        for (c in input[0].indices) {
            gammaRates += if (input.getNthBit(c).count { it == 0 } < input.size / 2) '1' else '0'
        }
        val epRates = gammaRates.map { Integer.parseInt(it.toString()) xor 1 }
        val gRates = gammaRates.map { Integer.parseInt(it.toString(), 2) }
        return epRates.toDecimal() * gRates.toDecimal()
    }

    fun part2(input: List<String>): Int {
        val o = calculate(input, TaskEnum.Oxygen)
        val co2 = calculate(input, TaskEnum.CO2)
        return o * co2
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))


}

fun List<Int>.toDecimal() = joinToString(separator = "").toInt(2)
fun List<String>.getNthBit(i: Int): List<Int> {
    return this.map { Integer.parseInt(it[i].toString()) }
}

fun calculate(i: List<String>, t: TaskEnum): Int {
    val r: Int
    val e: Int

    if (t == TaskEnum.Oxygen) {
        r = 0
        e = 1
    } else {
        r = 1
        e = 0
    }

    var l = i
    var n = l.size
    var index = 0
    while (n > 1) {
        val domValue = if (l.getNthBit(index)
                .count { it == 0 } > l.size / 2.0
        ) r else e
        l.filter { Integer.parseInt(it[index].toString()) == domValue }.also { l = it }
        n = l.size
        index++
    }
    return l[0].map { it.toString().toInt() }.toDecimal()
}

enum class TaskEnum {
    Oxygen, CO2
}
fun main() {
    fun part1(input: List<String>): Int {
        var gammaRates = ""
        for (c in input[0].indices) {
            var zeroBit = 0
            for (v in input) {
                if (v[c] == '0') {
                    zeroBit++
                }
            }
            if (zeroBit < input.size / 2) {
                gammaRates += '1'
            } else {
                gammaRates += '0'
            }

        }

        val epRates = gammaRates.map { Integer.parseInt(it.toString()) xor 1 }
        val gRates = gammaRates.map { Integer.parseInt(it.toString(), 2) }
        return epRates.toDecimal() * gRates.toDecimal()
    }

    fun part2(input: List<String>): Int {
        val oxigengen = OxygenGenerator(input)
        val co2 = CO2(input)
        return oxigengen * co2
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

fun OxygenGenerator(i: List<String>): Int {
    var l = i
    var n = l.size
    var index = 0
    while (n > 1) {
        val domValue = if (l.getNthBit(index)
                .count { it == 0 } > l.size / 2.0
        ) 0 else 1
        l.filter { Integer.parseInt(it[index].toString()) == domValue }.also { l = it }
        n = l.size
        index++
    }
    if (l.size != 1) {
        throw Error("no success")
    }
    return l[0].map { it.toString().toInt() }.toDecimal()
}

fun CO2(i: List<String>): Int {
    var l = i
    var n = l.size
    var index = 0
    while (n > 1) {
        val domValue = if (l.getNthBit(index)
                .count { it == 0 } > l.size / 2.0
        ) 1 else 0
        l.filter { Integer.parseInt(it[index].toString()) == domValue }.also { l = it }
        n = l.size
        index++
    }
    return l[0].map { it.toString().toInt() }.toDecimal()
}
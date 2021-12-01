fun main() {
    fun part1(input: List<String>): Int {
        var times = 0
        var curr = input[0].toInt()
        for (r in input) {
            if (r.toInt() > curr) {
                times++
            }
            curr = r.toInt()
        }
        return times
    }

    fun part2(input: List<String>): Int {
        var times = 0
        var currSum = 0
        run breaker@{
            input.forEachIndexed { i, s ->
                // outer
                if (i == input.size - 2) return@breaker

                var itemSum = 0
                for (j in input.subList(i, i + 3)) {
                    // inner
                    itemSum += j.toInt()
                }
                if (i != 0 && itemSum > currSum) {
                    times++
                }
                currSum = itemSum
            }
        }
        return times
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)
}

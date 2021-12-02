fun main() {
    fun part1(input: List<String>): Int {
        var hp = 0
        var depth = 0

        for (line in input) {
            var direction: String
            var numeric: Number

            val split = line.split(" ")
            direction = split[0]
            numeric = split[1].toInt()

            when (direction) {
                "forward" -> hp += numeric
                "down" -> depth += numeric
                "up" -> depth -= numeric
            }
        }

        return hp * depth
    }

    fun part2(input: List<String>): Int {
        val submarineDirDepAim = SubmarineDirDepAim()
        for (line in input) {
            var direction: String
            var numeric: Int
            val split = line.split(" ")
            direction = split[0]
            numeric = split[1].toInt()

            when (direction) {
                "forward" -> {
                    submarineDirDepAim.hp += numeric
                    submarineDirDepAim.depth += submarineDirDepAim.aim * numeric
                }
                "down" -> submarineDirDepAim.aim += numeric
                "up" -> submarineDirDepAim.aim -= numeric
            }
        }
        return submarineDirDepAim.hp * submarineDirDepAim.depth
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

data class SubmarineDirDepAim(var hp: Int = 0, var depth: Int = 0, var aim: Int = 0)

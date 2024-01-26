class BullsAndCowsGame {
    public var secretNumber: String = "" // Тайное число

    fun generateSecretNumber() {
        do {
            secretNumber = (0..9999).random().toString().padStart(4, '0') // Генерация числа, начинающегося с 0
        } while(secretNumber.toSet().size < 4) // Проверка на неповторяющиеся цифры
    }

    fun makeGuess(guess: String): Pair<Int, Int> {
        var bulls = 0
        var cows = 0

        for (i in guess.indices) {
            if (guess[i] == secretNumber[i]) {
                bulls++
            } else if (secretNumber.contains(guess[i])) {
                cows++
            }
        }

        return bulls to cows
    }
}
fun main() {
    val game = BullsAndCowsGame()
    game.generateSecretNumber()

    println("Давай сыграем в игру \"Быки и коровы\". Я загадал четырёхзначное число. Попробуй угадать его!")

    do {
        print("Введи свою попытку: ")
        val guess = readLine() ?: ""

        if (guess.length != 4 || !guess.all { it.isDigit() }) {
            println("Неправильный формат. Введите четырёхзначное число.")
            continue
        }

        val (bulls, cows) = game.makeGuess(guess)
        println("Результат: $bulls быков и $cows коров.")

        if (bulls == 4) {
            println("Поздравляю! Ты угадал число: ${game.secretNumber}")
            break
        }
    } while (true)
}

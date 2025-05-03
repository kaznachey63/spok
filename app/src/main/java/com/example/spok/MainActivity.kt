package com.example.spok

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.spok.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userChoice: GameItems = GameItems.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvPayerChoice.setTextColor(ContextCompat.getColor(this, R.color.blue))
        binding.tvBotChoice.setTextColor(ContextCompat.getColor(this, R.color.red))


        binding.lizardButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.rockButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.paperButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.spockButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.scissorsButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.playButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
    }

    //  метод, который вызывается при нажатии на кнопку выбора
    fun setUserChoice(control: View) {
        binding.lizardButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))
        binding.rockButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))
        binding.paperButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))
        binding.spockButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))
        binding.scissorsButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))

        control.setBackgroundColor(ContextCompat.getColor(control.context, R.color.orange))

        userChoice = when (control.id) {
            binding.lizardButton.id -> GameItems.LIZARD
            binding.rockButton.id -> GameItems.ROCK
            binding.paperButton.id -> GameItems.PAPER
            binding.spockButton.id -> GameItems.SPOCK
            binding.scissorsButton.id -> GameItems.SCISSORS
            else -> GameItems.NONE
        }
    }

    // метод, который запускает игру
    fun startGame(control: View) {
        binding.lizardButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))
        binding.rockButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))
        binding.paperButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))
        binding.spockButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))
        binding.scissorsButton.setBackgroundColor(ContextCompat.getColor(control.context, R.color.purple))

        if (userChoice == GameItems.NONE) {
            binding.tvGameResult.text= "Сделайте свой выбор"
            return
        }

        val botChoice = getItemForBot()
        val result = opredWinner(userChoice, botChoice)

        binding.tvPayerChoice.text= "Игрок выбрал ${getChoiceName(userChoice)}"
        binding.tvBotChoice.text= "Враг выбрал ${getChoiceName(botChoice)}"

        binding.tvGameResult.text= when (result) {
            1 -> "Игрок победил!"
            0 -> "Ничья"
            -1 -> "Враг победил"
            else -> "Ошибка игры"
        }
    }

    // метод, генерирует случайный выбор для бота
    private fun getItemForBot(): GameItems {
        val result = Random.nextInt(0, 5)
        return when (result) {
            0 -> GameItems.ROCK
            1 -> GameItems.PAPER
            2 -> GameItems.SCISSORS
            3 -> GameItems.LIZARD
            4 -> GameItems.SPOCK
            else -> GameItems.NONE
        }
    }

    // метод для определения победителя на основе выбора игрока и бота
    private fun opredWinner(userChoice: GameItems, botChoice: GameItems): Int {
        return when {
            userChoice == botChoice -> 0  // ничья
            botChoice in userChoice.defeats -> 1  // победа игрока
            else -> -1  // победа бота
        }
    }

    // Функция для перевода выбора в строку на русском
    private fun getChoiceName(choice: GameItems): String {
        return when (choice) {
            GameItems.ROCK -> "Камень"
            GameItems.PAPER -> "Бумага"
            GameItems.SCISSORS -> "Ножницы"
            GameItems.LIZARD -> "Ящерица"
            GameItems.SPOCK -> "Спок"
            else -> "Не выбран"
        }
    }
}
package com.example.spok

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.spok.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var userChoice: GameItems = GameItems.NONE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.lizardButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.rockButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.paperButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.spockButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.scissorsButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))
        binding.playButton.setBackgroundColor(ContextCompat.getColor(this, R.color.purple))

        binding.tvPayerChoice.setTextColor(ContextCompat.getColor(this, R.color.blue))
        binding.tvBotChoice.setTextColor(ContextCompat.getColor(this, R.color.red))
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
            binding.tvGameResult.text = "Сделайте свой выбор"
            return
        }

        val botChoice = getItemForBot()
        val result = userChoice.getOutcomeAgainst(botChoice)

        binding.tvPayerChoice.text = "Игрок выбрал ${getChoiceName(userChoice)}"
        binding.tvBotChoice.text = "Враг выбрал ${getChoiceName(botChoice)}"

        binding.tvGameResult.text = when (result) {
            GameOutcome.WIN -> "Игрок победил!"
            GameOutcome.LOSE -> "Враг победил!"
            GameOutcome.DRAW -> "Ничья!"
        }
    }

    // метод, генерирует случайный выбор для бота
    private fun getItemForBot(): GameItems {
        val result = (0..4).random()
        return when (result) {
            0 -> GameItems.ROCK
            1 -> GameItems.PAPER
            2 -> GameItems.SCISSORS
            3 -> GameItems.LIZARD
            4 -> GameItems.SPOCK
            else -> GameItems.NONE
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
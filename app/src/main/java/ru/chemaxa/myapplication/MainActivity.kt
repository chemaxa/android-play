package ru.chemaxa.myapplication

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View; // подключаем класс View для обработки нажатия кнопки
import android.widget.EditText; // подключаем класс EditText


class MainActivity : AppCompatActivity() {
    companion object {
        val EXTRA_MESSAGE = "EXTRA_MESSAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sendMessage(view: View) {
        // действия, совершаемые после нажатия на кнопку
        // Создаем объект Intent для вызова новой Activity
        val intent = Intent(this, DisplayMessageActivity::class.java)
        // Получаем текстовое поле в текущей Activity
        val editText = findViewById(R.id.edit_message) as EditText
        // Получае текст данного текстового поля
        val message = editText.text.toString()
        // Добавляем с помощью свойства putExtra объект - первый параметр - ключ,
        // второй параметр - значение этого объекта
        intent.putExtra(EXTRA_MESSAGE, message)
        // запуск activity
        startActivity(intent)
    }

}

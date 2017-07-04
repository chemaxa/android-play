package ru.chemaxa.myapplication
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView



class DisplayMessageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Получаем сообщение из объекта intent
        val intent = intent
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE)

        // Создаем текстовое поле
        val textView = TextView(this)
        textView.textSize = 40f
        textView.text = message

        // Устанавливаем текстовое поле в системе компоновки activity
        setContentView(textView)
    }
}

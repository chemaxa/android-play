package ru.chemaxa.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    lateinit var resultField: TextView // текстовое поле для вывода результата
    lateinit var inputField: TextView // текстовое поле для вывода введенных символов
    var operand: Double? = null  // операнд операции
    var lastOperation = "=" // последняя операция

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // получаем все поля по id из activity_main.xml
        resultField = findViewById(R.id.resultField) as TextView
        inputField = findViewById(R.id.inputField) as TextView
    }

//    // сохранение состояния
//    override fun onSaveInstanceState(outState: Bundle) {
//        outState.putString("OPERATION", lastOperation)
//        if (operand != null) {
//            outState.putDouble("OPERAND", operand as Double)
//        }
//        super.onSaveInstanceState(outState)
//    }
//
//    // получение ранее сохраненного состояния
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        lastOperation = savedInstanceState.getString("OPERATION")
//        operand = savedInstanceState.getDouble("OPERAND")
//        resultField.text = operand.toString()
//        resultField.text = lastOperation
//    }

    // обработка нажатия на числовую кнопку
    fun onNumber(view: View) {
        val button = view as Button
        val number = button.text.toString().toDouble()
        inputField.text = inputField.text.toString() + button.text.toString()
        if (lastOperation == "=" && operand == null) {
            operand = number;
        }
        if (lastOperation != "=") {
            resultField.text = performOperation(number, lastOperation)
        }
    }

    // обработка нажатия на кнопку операции
    fun onOperation(view: View) {
        val button = view as Button
        val op = if (button.id == R.id.button_reset) "clr" else button.text.toString()
        lastOperation = op
        if (op == "clr") {
            inputField.text = ""
            resultField.text = ""
            lastOperation = "="
            operand = null
            return
        }
        if (lastOperation == "=") {
            resultField.text = operand.toString()
        }else {
            inputField.text = inputField.text.toString() + op
        }
    }

    private fun performOperation(number: Double?, operation: String): String {
        if (number != null) {
            when (operation) {
                "=" -> operand = number
                "/" -> if (number == 0.toDouble()) {
                    operand = 0.0
                } else {
                    operand = operand!! / number
                }
                "*" -> operand = operand!! * number
                "+" -> operand = operand!! + number
                "-" -> operand = operand!! - number
            }
        }
        return operand.toString()
    }
}

package ru.chemaxa.myapplication

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

class MainActivity : AppCompatActivity() {

    var resultField: TextView? = null // текстовое поле для вывода результата
    var operand: Double? = null  // операнд операции
    var lastOperation = "=" // последняя операция

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // получаем все поля по id из activity_main.xml
        resultField = findViewById(R.id.resultField) as TextView
    }

    // сохранение состояния
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString("OPERATION", lastOperation)
        if (operand != null) {
            outState.putDouble("OPERAND", operand as Double)
        }
        super.onSaveInstanceState(outState)
    }

    // получение ранее сохраненного состояния
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        lastOperation = savedInstanceState.getString("OPERATION")
        operand = savedInstanceState.getDouble("OPERAND")
        resultField?.text = operand.toString()
        resultField?.text = lastOperation
    }

    // обработка нажатия на числовую кнопку
    fun onNumber(view: View) {

        val button = view as Button
        resultField?.append(button.getText())

        if (lastOperation == "=" && operand != null) {
            operand = null
        }
    }

    // обработка нажатия на кнопку операции
    fun onOperationClick(view: View) {

        val button = view as Button
        val op = button.getText().toString()
        var number = resultField?.text.toString()
        // если введенно что-нибудь
        if (number.isNotEmpty()) {
            number = number.replace(',', '.')
            try {
                performOperation(java.lang.Double.valueOf(number), op)
            } catch (ex: NumberFormatException) {
                resultField?.setText("")
            }

        }
        lastOperation = op
        resultField?.text = lastOperation
    }

    private fun performOperation(number: Double?, operation: String) {

        // если операнд ранее не был установлен (при вводе самой первой операции)
        if (operand == null) {
            operand = number
        } else {
            if (number != null) {
                if (lastOperation == "=") {
                    lastOperation = operation
                }
                when (lastOperation) {
                    "=" -> operand = number
                    "/" -> if (number == 0.toDouble()) {
                        operand = 0.0
                    } else {
                        operand = operand as Double / number
                    }
                    "*" -> operand = operand as Double * number
                    "+" -> operand = operand as Double + number
                    "-" -> operand = operand as Double - number
                }
            }
        }

        resultField?.text = operand.toString().replace('.', ',')
        resultField?.setText("")
    }
}
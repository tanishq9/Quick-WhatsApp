package com.boss.whatsappopener

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.text.isDigitsOnly

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val editText = findViewById(R.id.num) as EditText
        val button = findViewById(R.id.button) as Button

        button.setOnClickListener {
            var number: String = editText.text.toString()
/*
            if (intent.action == Intent.ACTION_PROCESS_TEXT) {
                number = intent.getCharSequenceExtra(Intent.EXTRA_PROCESS_TEXT).toString();
            }
*/

            if (number.isDigitsOnly()) {
                startWhatsapp(number);
            } else {
                Toast.makeText(this, "Please check the number", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private fun startWhatsapp(number: String) {
        val intent = Intent(Intent.ACTION_VIEW);
        intent.setPackage("com.whatsapp");
        // +91 10 digits followed
        val data: String = if (number[0] == '+') {
            number.substring(1);
        } else if (number.length == 10) {
            "91" + number;
        } else {
            number;
        }
        intent.data = Uri.parse("https://wa.me/$data");
        if (packageManager.resolveActivity(intent, 0) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, "Please install whatsapp", Toast.LENGTH_SHORT).show();
        }
        finish();
    }

}

package com.birzavit2004.texttospeechapp

import android.speech.tts.TextToSpeech
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    private var tts: TextToSpeech? = null
    private lateinit var editText: EditText
    private lateinit var buttonConvert: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.ediText)
        buttonConvert = findViewById(R.id.buttonConvert)

        tts = TextToSpeech(this, this)

        buttonConvert.setOnClickListener {
            speakOut(editText.text.toString())
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            tts?.setLanguage(Locale("es","ES"))
        }
    }

    override fun onDestroy() {
        tts?.stop()
        tts?.shutdown()
        super.onDestroy()
    }

    fun speakOut(text: String) {
        tts?.speak(text, TextToSpeech.QUEUE_FLUSH, null)
    }
}

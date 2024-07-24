package com.example.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.SystemClock
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var isRunning = true
    private var counter = 0
    private lateinit var thread: Thread
    private lateinit var runnable:Runnable
    private lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler(Looper.getMainLooper())

        findViewById<Button>(R.id.buttonStart).setOnClickListener {

            Thread {
                isRunning = true
                while (isRunning) {
                    Thread.sleep(10)
                    counter++
                    handler.post{
                        findViewById<TextView>(R.id.textView).text = "Counter value: ${counter}"
                    }
                    if(Thread.currentThread().isAlive){
                        println("Thread is Alive")
                    }
                }
            }.start()
        }

        findViewById<Button>(R.id.buttonStop).setOnClickListener {
            isRunning = false
        }
    }
}

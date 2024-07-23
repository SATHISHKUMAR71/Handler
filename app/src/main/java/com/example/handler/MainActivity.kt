package com.example.handler

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private var isRunning = false
    private var counter = 0
    private lateinit var thread: Thread
    private lateinit var runnable:Runnable
    private lateinit var handler:Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handler = Handler(Looper.getMainLooper())

        findViewById<Button>(R.id.buttonStart).setOnClickListener {
            isRunning = true
            thread = Thread {
                while (isRunning) {
                    try {
                        Thread.sleep(10) // Simulate some work
                        handler.post(kotlinx.coroutines.Runnable {
                            findViewById<TextView>(R.id.textView).text = "Counter value: $counter"
                        })
                    } catch (e: InterruptedException) {
                        // Handle interruption if needed
                        println("Interrupted Exception")
                        break
                    }
                }
            }
            thread.start()
        }

        findViewById<Button>(R.id.buttonStop).setOnClickListener {
            isRunning = false
            thread.interrupt() // Interrupt the thread if it's sleeping
        }
    }
}

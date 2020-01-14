package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity() {
    lateinit var benderObj: Bender
    lateinit var tvText: TextView
    lateinit var ivSend: ImageView
    lateinit var ivBender: ImageView
    lateinit var etMessage: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        tvText = tv_text
        ivSend = iv_send
        ivBender = iv_bender
        etMessage = et_message

        val status = savedInstanceState?.getString("status") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("question") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status),Bender.Question.valueOf(question))

        val (r,g,b) = benderObj.status.color
        ivBender.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)

        tvText.text = benderObj.askQuesiton()
        ivSend.setOnClickListener {
            val (phrase, color) = benderObj.listenAnswer(etMessage.text.toString().toLowerCase())
            etMessage.setText("")
            val (r,g,b) = color
            ivBender.setColorFilter(Color.rgb(r,g,b),PorterDuff.Mode.MULTIPLY)
            tvText.text = phrase
        }

    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("status", benderObj.status.name)
        outState?.putString("question", benderObj.question.name)

    }
}

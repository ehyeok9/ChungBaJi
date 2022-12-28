package com.example.temp.tour_add.date_choice

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.temp.MainActivity
import com.example.temp.R
import com.example.temp.databinding.ActivityDateChoiceBinding
import com.example.temp.tour_add.date_choice.models.request.TourAddRequest
import com.example.temp.tour_add.date_choice.models.response.TourAddResponse
import com.example.temp.tour_add.date_choice.network.DateChoiceActivityInterface
import com.example.temp.tour_add.date_choice.network.DateChoiceService
import java.util.*


class DateChoiceActivity:AppCompatActivity(),DateChoiceActivityInterface {
    private val TAG="DateChoiceActivityTAG"
    private lateinit var binding: ActivityDateChoiceBinding
    private var receiveIntent: Intent?=null
    private var country:String=""
    private var isFinish=false

    private var startDate=""
    private var endDate=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDateChoiceBinding.inflate(layoutInflater); // 1
        setContentView(binding.root); //

        receiveIntent=intent
        if(receiveIntent!=null){
            country= receiveIntent?.getStringExtra("country").toString()
        }
        binding.dateChoiceLayoutMain.visibility=View.GONE

        setListener()

    }


    private fun setListener(){
        binding.dateChoiceImgBack.setOnClickListener{
            finish()
        }

        val c: Calendar = Calendar.getInstance()
        val mYear: Int = c.get(Calendar.YEAR)
        val mMonth: Int = c.get(Calendar.MONTH)
        val mDay: Int = c.get(Calendar.DAY_OF_MONTH)

        binding.dateChoiceLayoutStart.setOnClickListener{
            DatePickerDialog(this,
                { view, year, month, dayOfMonth -> binding.dateChoiceTvStartMain.setText(year.toString() + "." + (month+1) + "." + dayOfMonth.toString()) },
                mYear,
                mMonth,
                mDay
            ).show()
        }

        binding.dateChoiceLayoutEnd.setOnClickListener{
            DatePickerDialog(this,
                { view, year, month, dayOfMonth -> binding.dateChoiceTvEndMain.setText(year.toString() + "." + (month+1) + "." + dayOfMonth.toString()) },
                mYear,
                mMonth,
                mDay
            ).show()
        }

        binding.dateChoiceTvNo.setOnClickListener {
            setActivate(binding.dateChoiceTvNo)
            setDisable(binding.dateChoiceTvYes)
            isFinish=true
            checkFinish()
            binding.dateChoiceLayoutMain.visibility=View.GONE

        }

        binding.dateChoiceTvYes.setOnClickListener {
            setActivate(binding.dateChoiceTvYes)
            setDisable(binding.dateChoiceTvNo)
            isFinish=true
            checkFinish()
            binding.dateChoiceLayoutMain.visibility=View.VISIBLE
        }

        binding.dateChoiceTvFinish.setOnClickListener {
            if(isFinish){
                DateChoiceService(this).tryPostTourAdd(TourAddRequest(country,binding.dateChoiceTvStartMain.text.toString(),binding.dateChoiceTvEndMain.text.toString()))
            }
        }
    }


    private fun setActivate(view: View){
        if(view is TextView){
            view.background=resources.getDrawable(R.drawable.bg_activate,null)
            view.setTextColor(resources.getColor(R.color.white,null))
        }

    }

    private fun setDisable(view:View){
        if(view is TextView){
            view.background=resources.getDrawable(R.drawable.bg_disable,null)
            view.setTextColor(resources.getColor(R.color.black,null))
        }
    }

    private fun checkFinish(){
        if(isFinish){
            setActivate(binding.dateChoiceTvFinish)
        }else{
            setDisable(binding.dateChoiceTvFinish)
        }
    }

    override fun onPostTourAddSuccess(response: TourAddResponse) {
        Log.d(TAG,response.toString())

        var intent=Intent(this,MainActivity::class.java)
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP //액티비티 스택제거
        startActivity(intent)
    }

    override fun onPostTourAddFailure(code: Int, message: String) {
        Log.d(TAG,"[onPostTourAddFailure] : $code , $message")
    }


}
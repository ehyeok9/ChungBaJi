package com.example.temp.todo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.temp.databinding.FragmentTodoDocBinding
import com.example.temp.todo.models.passport.response.CountryPassPortResponse
import com.example.temp.todo.models.response.ContryEntryInfoResponse
import com.example.temp.todo.todo_details.TodoDetailsActivity
import com.example.temp.todo.todo_doc_network.TodoDocFragmentInterface
import com.example.temp.todo.todo_doc_network.TodoDocService

class TodoDocFragment : Fragment(),TodoDocFragmentInterface {
    private val TAG="TodoDocFragmentTAG"
    lateinit var todoAdapter: TodoAdapter

    private var covidContent:String="null"
    private var passportContent:String="null"
    private lateinit var binding : FragmentTodoDocBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTodoDocBinding.inflate(layoutInflater)
        initRecycler()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        TodoDocService(this@TodoDocFragment).tryGetContryEntryInfo("ZHjMzIWX5bq/z7LHhg8Aa9pQ+zTXdLoU71TjpZl6pcECtIPV9hPEu2xQTbcBHWeTxff7YpY72QPyISTtW7m51w==",
            1,10,"JSON","일본")

        TodoDocService(this@TodoDocFragment).tryGetCountryPassPort("ZHjMzIWX5bq/z7LHhg8Aa9pQ+zTXdLoU71TjpZl6pcECtIPV9hPEu2xQTbcBHWeTxff7YpY72QPyISTtW7m51w==",
        1,10,"일본")

        setListener()

    }
    private fun setListener(){
        (binding.rv.adapter as TodoAdapter).setOnCountryClickListener(object:TodoAdapter.OnCovidClickListener{
            override fun onCovidClick(title:String) {
                var intent = Intent(requireContext(), TodoDetailsActivity::class.java)

                when(title){
                    "백신증명서" -> {intent.putExtra("content",covidContent)}
                    "여권/비자" -> {intent.putExtra("content",passportContent)}
                }

                startActivity(intent)
            }

        })
    }

    private fun initRecycler(){
        val list = ArrayList<checkboxData>()
        list.add(checkboxData(title="여권/비자", check = false))
        list.add(checkboxData(title="백신증명서", check = false))
//        list.add(checkboxData(title="국제운전면허증", check = false))
//        list.add(checkboxData(title="숙소 바우처", check = false))
//        list.add(checkboxData(title="dd", check = false))
//        list.add(checkboxData(title="dd", check = false))

        todoAdapter= TodoAdapter(list)
        binding.rv.adapter=todoAdapter
        todoAdapter.notifyDataSetChanged()


    }

    override fun onContryEntryInfoSuccess(response: ContryEntryInfoResponse) {
        covidContent=response.data[0].txt_origin_cn
//        Log.d(TAG,response.toString())
    }

    override fun onContryEntryInfoFailure(message: String, code: Int) {
//        Log.d(TAG,"[onContryEntryInfoFailure] : $code , $message")

    }

    override fun onGetCountryPassPortSuccess(response: CountryPassPortResponse) {
        passportContent="일반여권 입국허가 기간\n"+"- "+response.data[0].dplmt_pspt_visa_cn+"\n\n"+"무비자 입국근거내용\n"+"- "+response.data[0].nvisa_entry_evdc_cn  +"\n\n"+ "비고\n"+"- "+
                response.data[0].remark
        Log.d(TAG,passportContent)

    }

    override fun onGetCountryPassPortFailure(message: String, code: Int) {
        Log.d(TAG,"[onGetCountryPassPortFailure] : $code , $message")



    }
}
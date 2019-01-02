package com.sopt.befit.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_my_size_add.*
import org.jetbrains.anko.toast

class MySizeAddActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_my_size_add)

        setSpinner()
    }

    private fun setSpinner() {
        val dataList: ArrayList<String> = ArrayList()
        dataList.add("xs")
        dataList.add("s")
        dataList.add("m")
        dataList.add("l")

        sp_my_size_add_select_size.adapter= ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dataList)

        sp_my_size_add_select_size.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                toast("선택된 아이템 : "+sp_my_size_add_select_size.getItemAtPosition((position)))
                //누른 값에 맞게 서버로 부터 상세 사이즈 값을 받아와 텍스트값을 바꿔줌
            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }
}

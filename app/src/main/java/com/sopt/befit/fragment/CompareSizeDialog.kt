package com.sopt.befit.Fragment

import android.R
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_select_brand_goods_window.*
import kotlinx.android.synthetic.main.fragment_compare_size.*
import org.jetbrains.anko.support.v4.toast

class CompareSizeDialog : DialogFragment(){
    private fun setSpinner() {
        val dataList: ArrayList<String> = ArrayList()
        dataList.add("유니클로 COMPACT COTTON터틀넥T(긴팔) L")
        dataList.add("스파오 COMPACT COTTON터틀넥T(긴팔) L")
        dataList.add("지오다노 COMPACT COTTON터틀넥T(긴팔) L")
        dataList.add("에잇세컨즈 COMPACT COTTON터틀넥T(긴팔) L")

        sp_compare_size.adapter = ArrayAdapter<String>(activity!!, R.layout.simple_list_item_single_choice, dataList)
        //sp_my_size_add_select_size.adapter = SelectSizeSpinnerAdapterval(this, dataList)
        sp_compare_size.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                toast("선택된 아이템 : " + sp_my_size_add_select_size.getItemAtPosition((position)))
                //누른 값에 맞게 서버로 부터 상세 사이즈 값을 받아와 텍스트값을 바꿔줌

            }

            override fun onNothingSelected(parent: AdapterView<*>) {

            }
        })

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSpinner()
    }
}
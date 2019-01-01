package com.sopt.befit.Fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.adapter.Expandable
import com.sopt.befit.R
import kotlinx.android.synthetic.main.fragment_mypage.*

class MypageFragment :Fragment(){
    val header :MutableList<String> = ArrayList()
    val body : MutableList<MutableList<String>> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_mypage, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setlistview()
    }

    fun setlistview(){

        val fashion : MutableList<String> = ArrayList()
        val mysize : MutableList<String> = ArrayList()
        val set : MutableList<String> = ArrayList()

        val cus : MutableList<String> = ArrayList()
        cus.add("QnA")



        header.add("나의 패션 취향")
        header.add("나의 사이즈 정보")
        header.add("통합 계정 설정")
        header.add("고객센터")


        body.add(fashion)
        body.add(mysize)
        body.add(set)
        body.add(cus)


        elv_my_page_list.setAdapter(Expandable(activity!!,header,body))

    }

}
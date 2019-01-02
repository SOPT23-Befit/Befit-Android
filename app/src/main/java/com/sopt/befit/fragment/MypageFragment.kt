package com.sopt.befit.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.befit.adapter.Expandable
import com.sopt.befit.R
import kotlinx.android.synthetic.main.activity_aaaamain.*
import kotlinx.android.synthetic.main.fragment_mypage.*
import org.jetbrains.anko.support.v4.toast

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
        cus.add("1:1 문의내역")
        cus.add("상품 QnA 내역")
        cus.add("공지사항")
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

        elv_my_page_list.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->


            Log.e("child click", "groupPosition $groupPosition, childPosition $childPosition, id $id")




            var gpos = groupPosition
            var cpos = childPosition


            if(gpos==0)
            {

                //선호브랜드페이지

            }
            if(gpos==1)
            {

                //나의 사이즈 정보페이지

            }


            if(gpos==2)
            {
                //회원정보 수정페이지 (회원정보 저장한채로)
            }

            if(gpos==3&&cpos==0)
            {
                toast("1:1 문의 내역")
            }
            return@setOnChildClickListener false


        }


    }

}
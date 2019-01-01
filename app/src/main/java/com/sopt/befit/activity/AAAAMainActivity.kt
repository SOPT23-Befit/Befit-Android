package com.sopt.befit.activity

import android.os.Bundle


import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageButton
import com.sopt.befit.adapter.Expandable
import com.sopt.befit.R
import com.sopt.befit.adapter.MyFragmentStatePagerAdapter
import kotlinx.android.synthetic.main.activity_aaaamain.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.tab_bar.*
import org.jetbrains.anko.colorAttr
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AAAAMainActivity : AppCompatActivity() {

    val header :MutableList<String> = ArrayList()

    val body : MutableList<MutableList<String>> = ArrayList()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aaaamain)


        configureBottomNavigation()


        ibtn_menu_open.setOnClickListener(){
            ibtn_menu_open.visibility=View.INVISIBLE
            tl_bottom_navi_act_bottom_menu.visibility=View.INVISIBLE
            category_menu.visibility=View.VISIBLE
        }

        ibtn_AAAA_main_act_jjim.setOnClickListener(){
            startActivity<JjimActivity>()
        }


        title = "Category"


        val new:MutableList<String> = ArrayList()
        val best : MutableList<String> = ArrayList()
        val women : MutableList<String> = ArrayList()
        women.add("Outer")
        women.add("Top")
        women.add("Bottom")
        women.add("Act")
        val men : MutableList<String> = ArrayList()
        men.add("Outer")
        men.add("Top")
        men.add("Bottom")
        men.add("Act")

        header.add("New")
        header.add("Best")
        header.add("Women")
        header.add("Men")



        body.add(new)
        body.add(best)
        body.add(women)
        body.add(men)




        nav_list.setAdapter(Expandable(this,header,body))



        nav_list.setOnChildClickListener { parent, v, groupPosition, childPosition, id ->


            Log.e("child click", "groupPosition $groupPosition, childPosition $childPosition, id $id")




            var gpos = groupPosition
            var cpos = childPosition


            if(gpos==2&&cpos==0)
            {

                toast("성공")

            }

            return@setOnChildClickListener false


        }

    }



    private fun configureBottomNavigation()
    {

//TabLayout에 붙일 layout을 찾아준 다음
        val bottomNaviLayout : View = this.layoutInflater.inflate(R.layout.tab_bar, null, false)
//탭 하나하나 TabLayout에 연결시켜줍니다.
        tl_bottom_navi_act_bottom_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_home) as ImageButton
        tl_bottom_navi_act_bottom_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_search) as ImageButton
        tl_bottom_navi_act_bottom_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_ranking) as ImageButton
        tl_bottom_navi_act_bottom_menu.getTabAt(3)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_ranking) as ImageButton
        tl_bottom_navi_act_bottom_menu.getTabAt(4)!!.customView = bottomNaviLayout.findViewById(R.id.ibtn_AAAA_main_act_mypage) as ImageButton

    }
}

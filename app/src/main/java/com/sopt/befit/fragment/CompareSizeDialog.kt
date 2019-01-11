package com.sopt.befit.fragment

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.JsonParser
import com.rd.PageIndicatorView


import com.sopt.befit.R
import com.sopt.befit.activity.ProductContentViewActivity
import com.sopt.befit.adapter.CompareSizeAdapter
import com.sopt.befit.get.ClosetDetail
import kotlinx.android.synthetic.main.dl_compare_size.*
import org.jetbrains.anko.support.v4.startActivity
import com.sopt.befit.R.id.pageIndicatorView
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.Gravity
import android.widget.*
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.animation.ProgressAnimation
import com.sopt.befit.get.Compare
import kotlinx.android.synthetic.main.fragment_compare_size.*
import org.jetbrains.anko.backgroundColor
import org.jetbrains.anko.textColor


class CompareSizeDialog() : DialogFragment() {

    lateinit var closetList : ArrayList<ClosetDetail>
    var product_idx = -1
    lateinit var measure : String
    lateinit var closetSize : ArrayList<String>
    lateinit var compareSizeAdapter : CompareSizeAdapter

    companion object {
        lateinit var instance : DialogFragment
    }
    fun checkBtnClick(){
        btn_dl_compare_size_check.setOnClickListener{
            // startActivity<ProductContentViewActivity>()
            dismiss()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View = inflater.inflate(R.layout.dl_compare_size, container, false)

        Glide.with(view.context)
                .load("https://s3.ap-northeast-2.amazonaws.com/befit-server/33.+jeansslackspants.png")
                .into(view!!.findViewById(R.id.iv_fragment_compare_size_my_size))

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        closetList = arguments!!.getSerializable("ClosetList") as ArrayList<ClosetDetail>

        //상품 정보 넘겨주기
//        product_idx = arguments!!.getInt("product_idx")
//        measure = arguments!!.getString("measure")

        measure = ProductContentViewActivity.instance.getCurrentProductData().measure.toString()

        measure = measure!!.replace(" ", "")

        var parser = JsonParser()
        var json = parser.parse(measure).asJsonObject

        closetSize = ArrayList<String>()

        for((index,measure) in json.entrySet().withIndex()){
            closetSize.add(measure.key)
        }

        instance = this
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        configureBottomNavigation()
        checkBtnClick()
        setSpinner(closetList)

        tv_fragment_compare_size_goods_name.text = ProductContentViewActivity.instance.getCurrentProductData().name
    }

    private fun configureBottomNavigation() {
        // var count = 2
        compareSizeAdapter = CompareSizeAdapter(childFragmentManager,closetSize.size,closetList.get(0).closet_idx)
        vp_compare_size_view_pager.adapter = compareSizeAdapter
        vp_compare_size_view_pager.offscreenPageLimit = closetSize.size
//        vp_compare_size_view_pager.setCurrentItem(0,false)
        pageIndicatorView.count = closetSize.size
        pageIndicatorView.selection = 1

        vp_compare_size_view_pager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {/*empty*/
            }

            override fun onPageSelected(position: Int) {
                pageIndicatorView.selection = position
                var current = vp_compare_size_view_pager.adapter!!.instantiateItem(vp_compare_size_view_pager,position) as CompareSizeFragment1
                setClosetComapreData(current.getCompareSizeData(),position)
                Log.d("Spinner new",position.toString()+current.compareData.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {/*empty*/
            }
        })

        //      pageIndicatorView.setCount(closetSize.size) // specify total count of indicators
        //     pageIndicatorView.setSelection(1)       //vp_bottom_navi_act_frag_pager.offscreenPageLimit = 3
        // ViewPager와 Tablayout을 엮어줍니다!
//        tl_bottom_navi_act_bottom_menu.setupWithViewPager(vp_compare_size_view_pager)
//        //TabLayout에 붙일 layout을 찾아준 다음
//        val bottomNaviLayout: View = this.layoutInflater.inflate(R.layout.bottom_navigation_tab, null, false)
//        //탭 하나하나 TabLayout에 연결시켜줍니다.
//        tl_bottom_navi_act_bottom_menu.getTabAt(0)!!.customView = bottomNaviLayout.findViewById(R.id.compare_size_number1) as RelativeLayout
//        tl_bottom_navi_act_bottom_menu.getTabAt(1)!!.customView = bottomNaviLayout.findViewById(R.id.compare_size_number2) as RelativeLayout
//        tl_bottom_navi_act_bottom_menu.getTabAt(2)!!.customView = bottomNaviLayout.findViewById(R.id.compare_size_number3) as RelativeLayout
    }

    fun refreshViewPager(){
        compareSizeAdapter.notifyDataSetChanged()
    }


    private fun setSpinner(dataList: ArrayList<ClosetDetail>) {
        val closetNameList: ArrayList<String> = ArrayList()

        for (i in 0 until dataList.size) {
            closetNameList.add(dataList.get(i).name)
        }
        if (sp_compare_size != null) {
            sp_compare_size.adapter = ArrayAdapter<String>(activity!!, android.R.layout.simple_list_item_single_choice, closetNameList)
            //sp_my_size_add_select_size.adapter = SelectSizeSpinnerAdapterval(this, dataList)
            sp_compare_size.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    //toast("선택된 아이템 : " + sp_compare_size.getItemAtPosition((position)))
                    //누른 값에 맞게 서버로 부터 상세 사이즈 값을 받아와 텍스트값을 바꿔줌
                    Log.d("Spinner",position.toString())
                    var closet_idx = dataList.get(position).closet_idx
                    //closetidx = 8
                    Log.d("spinner closteSize",closetSize.size.toString())
                    var newCompareSizeAdapter = CompareSizeAdapter(childFragmentManager,closetSize.size,closet_idx)
                    vp_compare_size_view_pager.adapter = newCompareSizeAdapter
                    var current = vp_compare_size_view_pager.adapter!!.instantiateItem(vp_compare_size_view_pager,vp_compare_size_view_pager.currentItem) as CompareSizeFragment1
                    setClosetComapreData(current.getCompareSizeData(),0)
                }

                override fun onNothingSelected(parent: AdapterView<*>) {

                }
            })
        }else{

        }
    }

    fun setClosetComapreData(compareData : Compare?,position : Int){
        tv_fragment_compare_size_Size.text = closetSize.get(position)
        if(compareData == null){

        } else {
            tv_fragment_compare_size_goods_name.text = ProductContentViewActivity.instance.getCurrentProductData().name
            tv_fragment_compare_size_percent.text = compareData.percent+"%"
            var animation = ProgressAnimation(progress, 2000)//2000은 2초
            animation.setProgress(compareData.percent.toInt())
            setCompareTable(compareData,position)
        }
    }

    fun setCompareTable(compareData: Compare,position: Int){
        layout_my_size_add_display_tvs.removeAllViews()
        Log.d("mmmmm",compareData.toString())
        var jsonString = compareData.measure.toString()
        Log.d("mmmmmm",jsonString)
        var parser = JsonParser()
        var measureObject = parser.parse(jsonString).asJsonObject
        var keyList = ArrayList<String>()
        var valueList = ArrayList<Double>()
        for ( (index,result) in measureObject.entrySet().withIndex()){
            keyList.add(result.key)
            Log.d("compare measure",keyList.get(index))
            if (result.value == null) {
                valueList.add(-99.0)
            } else {
                valueList.add(result.value.asDouble)
            }
        }

        var spinnerPosition = sp_compare_size.selectedItemPosition
        var closetMeasure = closetList.get(spinnerPosition).measure.toString()
        closetMeasure = closetMeasure!!.replace(" ","")
        var closetObject = parser.parse(closetMeasure).asJsonObject
        var closetMeasureList = ArrayList<String>()
        var closetKeyList = ArrayList<String>()
        for((index,result) in closetObject.entrySet().withIndex()){
            closetKeyList.add(result.key)
            if((""+result.value).equals("null")){
                closetMeasureList.add("")
            } else {
                closetMeasureList.add(result.value.asString+"cm")
            }
        }

        val row = arrayOfNulls<TableRow>(3)     // 테이블 ROW 생성

        for(i in 0 until 3){
            row.set(i, TableRow(activity))
            var params = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT)
            row.get(i)!!.layoutParams = params
            var textList = arrayOfNulls<TextView>(keyList.size)
            for(j in 0 until  keyList.size){
                when(i){
                    0->{
                        textList.set(j, TextView(activity))
                        textList.get(j)!!.text = keyList.get(j)
                        textList.get(j)!!.textColor = Color.parseColor("#191919")
                        textList.get(j)!!.gravity = Gravity.CENTER
                    }
                    1->{
                        textList.set(j, TextView(activity))
                        textList.get(j)!!.textColor = Color.parseColor("#7a36e4")
                        textList.get(j)!!.gravity = Gravity.CENTER
                        if(valueList.get(j) < 0.0){
                            textList.get(j)!!.text = Math.abs(valueList.get(j)).toString()+"cm 작음"
                        } else if(valueList.get(j) == 0.0){
                            textList.get(j)!!.text = "딱 맞음"
                        } else {
                            textList.get(j)!!.text = Math.abs(valueList.get(j)).toString()+"cm 큼"
                        }
                    }
                    2->{
                        textList.set(j, TextView(activity))
                        textList.get(j)!!.textColor = Color.parseColor("#000000")
                        textList.get(j)!!.gravity = Gravity.CENTER
                        var closetSize = ""+closetObject.get(keyList.get(j))
                        if(closetSize.equals("null")){
                            textList.get(j)!!.text = ""
                        } else {
                            textList.get(j)!!.text = closetSize+"cm"
                        }
                    }
                }
//                var childParams = TableLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT,1f)
//                textList.get(j)!!.layoutParams = childParams
                row.get(i)!!.addView(textList.get(j))
            }
            layout_my_size_add_display_tvs.addView(row.get(i))

            if(i<2){
                var lineLayout = View(context)
                lineLayout.backgroundColor = Color.parseColor("#dcdcdc")
                var lineParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,converDpToPixel(1f,context!!).toInt())
                lineLayout.layoutParams = lineParams
                layout_my_size_add_display_tvs.addView(lineLayout)
            }

            layout_my_size_add_display_tvs.isStretchAllColumns = true
        }

    }


    fun converDpToPixel(dp : Float, context : Context) : Float{
        var resource = context.resources
        var metrics =  resource.displayMetrics
        var px = dp * (metrics.densityDpi / 160f)
        return px
    }

    fun convertSpTOPixel(sp : Float, context : Context) : Float{
        val px = sp * resources.displayMetrics.scaledDensity
        return px
    }
}
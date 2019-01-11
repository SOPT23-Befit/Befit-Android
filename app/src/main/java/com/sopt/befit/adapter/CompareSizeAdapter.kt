package com.sopt.befit.adapter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v4.view.PagerAdapter
import android.util.Log
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.request.RequestOptions
import com.sopt.befit.fragment.CompareSizeFragment1
import com.sopt.befit.fragment.CompareSizeFragment2
import com.sopt.befit.fragment.CompareSizeFragment3
import com.sopt.befit.get.ClosetDetail
import android.util.SparseArray



class CompareSizeAdapter(fm: FragmentManager, val fragmentCount: Int,val closetIdx : Int) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment? {
//        when (position) {
//            0 -> return CompareSizeFragment1()
//            1 -> return CompareSizeFragment2()
//            2 -> return CompareSizeFragment3()
//            else -> return null
//        }
        var fragment : Fragment
        var bundle = Bundle()
        bundle.putInt("position",position)
        bundle.putInt("closetIdx",closetIdx)
        Log.d("fragment count",position.toString())
        fragment = CompareSizeFragment1()
        fragment.arguments = bundle

        return fragment
    }


//    val requestOptions = RequestOptions()
//            //        requestOptions.placeholder(R.drawable.기본적으로 띄울 이미지)
//            //        requestOptions.error(R.drawable.에러시 띄울 이미지)
//            //        requestOptions.override(150) Glide.with(ctx)
//            .setDefaultRequestOptions(requestOptions)
//            .load(dataList[position].b_photo)
//            .thumbnail(0.5f)
//            .into(holder.image)

    override fun getCount(): Int = fragmentCount

//    override fun getItemPosition(`object`: Any): Int {
//        return PagerAdapter.POSITION_NONE
//    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }
}
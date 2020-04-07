package com.jorgejy.offersandcoupons.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.jorgejy.offersandcoupons.R
import com.jorgejy.offersandcoupons.databinding.ActivityMainBinding
import com.jorgejy.offersandcoupons.model.Coupon
import com.jorgejy.offersandcoupons.viewmodel.CouponViewModel

class MainActivity : AppCompatActivity() {

    private var couponViewModel: CouponViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setupBindings(savedInstanceState)
    }

    fun setupBindings(savedInstanceState: Bundle?){
        var activityMainBinding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)
        activityMainBinding.setModel(couponViewModel)
        setupListUpdate()
    }

    fun setupListUpdate(){
        couponViewModel?.callCoupons()
        couponViewModel?.getCoupons()?.observe(this, Observer {
            coupons: List<Coupon> ->
            Log.w("COUPON", coupons.get(0).title)
            couponViewModel?.setCouponsInRecyclerAdapter(coupons)
        })
    }
}

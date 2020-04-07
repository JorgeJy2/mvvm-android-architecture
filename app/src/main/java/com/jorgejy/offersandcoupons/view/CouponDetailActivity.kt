package com.jorgejy.offersandcoupons.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.jorgejy.offersandcoupons.BR
import com.jorgejy.offersandcoupons.R
import com.jorgejy.offersandcoupons.viewmodel.CouponViewModel

class CouponDetailActivity : AppCompatActivity() {
    private var couponViewModel: CouponViewModel? = null
    private var position: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coupon_detail)
        position = intent.getIntExtra("POSITION", 1)
        setupBindings(savedInstanceState)
        Log.e("DETAIL", position.toString())
        var btnOpenOffer: Button = findViewById(R.id.btnOpenOffer)
        btnOpenOffer.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(couponViewModel?.getCouponAt(position)?.url)
            startActivity(openURL)
        }
    }
    fun setupBindings(savedInstanceState: Bundle?){
        var couponDetailActivity: com.jorgejy.offersandcoupons.databinding.ActivityCouponDetailBinding = DataBindingUtil.setContentView(this, R.layout.activity_coupon_detail)
        couponViewModel = ViewModelProviders.of(this).get(CouponViewModel::class.java)
        couponDetailActivity.model = couponViewModel
        couponDetailActivity.setVariable(BR.position, position)
    }
}

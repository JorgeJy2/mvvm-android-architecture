package com.jorgejy.offersandcoupons.viewmodel

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jorgejy.offersandcoupons.R
import com.jorgejy.offersandcoupons.model.Coupon
import com.jorgejy.offersandcoupons.model.CouponObservable
import com.jorgejy.offersandcoupons.view.RecyclerCouponsAdapter
import com.squareup.picasso.Picasso

private var couponObservable: CouponObservable  = CouponObservable()

class CouponViewModel : ViewModel() {
    // Manejar los estados de la vista..
    // Manejar conexiones con lo osbervables


    private var recyclerCouponsAdapter: RecyclerCouponsAdapter ? = null

    fun callCoupons() {
        couponObservable.callCoupons()
    }

    fun getCoupons(): MutableLiveData<List<Coupon>> {
        return couponObservable.getCoupons()
    }

    fun setCouponsInRecyclerAdapter(coupons: List<Coupon>){
        recyclerCouponsAdapter?.setCouponsList(coupons)
        recyclerCouponsAdapter?.notifyDataSetChanged()
    }

    fun getRecyclerCouponsAdapter(): RecyclerCouponsAdapter?{
        recyclerCouponsAdapter = RecyclerCouponsAdapter(this, R.layout.card_coupon)
        return recyclerCouponsAdapter
    }

    fun getCouponAt(position: Int): Coupon? {
        Log.i("GET COUPONS AT", position.toString())
        var coupons: List<Coupon>? = couponObservable.getCoupons().value
        Log.i("GET COUPONS INFO", coupons?.toString())
        return coupons?.get(position)
    }
}
@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String) {
    Picasso.get().load(url).into(imageView)
}

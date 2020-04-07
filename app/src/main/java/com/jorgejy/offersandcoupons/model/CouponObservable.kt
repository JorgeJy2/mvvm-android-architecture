package com.jorgejy.offersandcoupons.model

import androidx.databinding.BaseObservable
import androidx.lifecycle.MutableLiveData

class CouponObservable: BaseObservable() {
    //Repoitorio
    //ViewModel

    private var couponRepository: CouponRepository = CouponRepositoryImpl()

    fun callCoupons(){
        couponRepository.callCoupunsAPI()
    }

    fun getCoupons(): MutableLiveData<List<Coupon>> {
        return couponRepository.getCoupuns();
    }
}
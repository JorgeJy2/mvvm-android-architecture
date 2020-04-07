package com.jorgejy.offersandcoupons.view

import android.content.Intent
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import com.jorgejy.offersandcoupons.model.Coupon
import com.jorgejy.offersandcoupons.viewmodel.CouponViewModel

class RecyclerCouponsAdapter(var couponViewModel:CouponViewModel,  var resource: Int) : RecyclerView.Adapter<RecyclerCouponsAdapter.CardCouponHolder>() {

    var coupons : List<Coupon>? = null

    fun setCouponsList(coupons: List<Coupon>?){
        this.coupons = coupons
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CardCouponHolder {
        var layoutInflater: LayoutInflater = LayoutInflater.from(p0.context)
        var binding: ViewDataBinding = DataBindingUtil.inflate(layoutInflater, p1, p0, false)
        return CardCouponHolder(binding)

    }

    override fun getItemCount(): Int {
        return coupons?.size ?: 0
    }

    override fun onBindViewHolder(p0: CardCouponHolder, p1: Int){
       p0.setDataCard(couponViewModel, p1)
    }

    override fun getItemViewType(position: Int): Int {
        return getLayoutIdForPosition(position)
    }

    fun getLayoutIdForPosition(position: Int): Int{
        return resource
    }

    class CardCouponHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        init {
            binding.root.setOnClickListener(this)
        }
        private var binding: ViewDataBinding?  = null
        private var position: Number = 0
        init {
            this.binding = binding
        }
        fun setDataCard(couponViewModel:CouponViewModel, position: Int){
            binding?.setVariable(BR.model, couponViewModel)
            binding?.setVariable(BR.position, position)
            this.position = position
            binding?.executePendingBindings()
        }

        override fun onClick(p0: View?) {
            Log.e("CLICK", position.toString())
            val context = binding?.root?.context
            val showPhotoIntent = Intent(context, CouponDetailActivity::class.java)
            showPhotoIntent.putExtra("POSITION", position)
            context?.startActivity(showPhotoIntent)
        }


    }
}

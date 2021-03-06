package com.jorgejy.offersandcoupons.model

import com.google.gson.JsonObject
import java.lang.Exception
import java.text.ParseException
import java.text.SimpleDateFormat
import java.io.Serializable
import java.util.*

class Coupon(couponJson: JsonObject?) : Serializable {

    lateinit var id: String
    lateinit var imageUrl: String
    lateinit var title: String
    lateinit var descriptionShort: String
    lateinit var category: String
    lateinit var description:String
    lateinit var offer: String
    lateinit var website: String
    lateinit var endDate: String
    lateinit var url: String
    lateinit var store: String
    lateinit var termsAndConditions: String
    lateinit var offerValue: String;

    init {
        try {
            id                  = couponJson!!.get(ID).asString
            imageUrl           =  getPathImg(couponJson!!.get(IMAGE_URL).asString)
            title               = couponJson!!.get(TITLE).asString
            descriptionShort    = couponJson!!.get(DESCRIPTION_SHORT).asString
            category            = chunkWords(couponJson!!.get(CATEGORY).asString, ',', 1)
            description         = couponJson!!.get(DESCRIPTION).asString
            offer               = couponJson!!.get(OFFER).asString
            website             = couponJson!!.get(WEBSITE).asString
            endDate             = getFormatDate(couponJson!!.get(END_DATE).asString)
            url                 = couponJson!!.get(URL).asString
            store               = onylNameStore(couponJson!!.get(STORE).asString)
            termsAndConditions  = couponJson!!.get(TERMS_CONDITIONS).asString
            offerValue  = couponJson!!.get(OFFER_VALUE).asString
        }catch (e: Exception){
            e.printStackTrace()
        }


    }

    companion object {
        private val ID                  = "lmd_id"
        private val IMAGE_URL           = "image_url"
        private val TITLE               = "title"
        private val DESCRIPTION_SHORT   = "offer_text"
        private val CATEGORY            = "categories"
        private val DESCRIPTION         = "description"
        private val OFFER               = "offer"
        private val WEBSITE             = "store"
        private val END_DATE            = "end_date"
        private val URL                 = "url"
        private val STORE               = "store"
        private val TERMS_CONDITIONS    = "terms_and_conditions"
        private val OFFER_VALUE         = "offer_value"
    }

    private fun getFormatDate(dateCoupon:String):String {
        val format = SimpleDateFormat("yyyy-MM-dd")
        val dateFormat = SimpleDateFormat("dd MMMM yyyy")
        try {
            val parsedDateFormat = format.parse(dateCoupon)
            val cal = Calendar.getInstance()
            cal.time = parsedDateFormat
            return dateFormat.format(cal.time)
        } catch (e: ParseException) {
            e.printStackTrace()
            return ""
        }
    }


    private fun chunkWords(string: String, delimiter: Char,quantity: Int): String {
        val words = string.split(delimiter)
        var lengthWords= 0
        lengthWords = if (quantity >= words.size)
            words.size -1
        else
            quantity - 1

        var newString: String = ""
        for (i in 0..lengthWords){
            newString += words[i] + " "
        }

        return newString
    }

    private fun getPathImg(path:String): String {
        if(path.isEmpty()){
            return "https://thumbs.dreamstime.com/b/upset-magnifying-glass-cute-not-found-symbol-unsuccessful-s-upset-magnifying-glass-cute-not-found-symbol-unsuccessful-122205900.jpg"
        }
        return path
    }

    private  fun onylNameStore(nameStrore: String): String {
        val names = nameStrore.split(".")
        return names[0]
    }

    override fun toString(): String {
        return "Coupon(id='$id', title='$title')"
    }




}
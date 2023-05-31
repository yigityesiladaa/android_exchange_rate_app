package com.example.odev_5.services

import android.util.Log
import com.example.odev_5.models.Currency

class CurrencyService {

    private val url = "https://www.tcmb.gov.tr/kurlar/today.xml"
    private val xmlService = XmlService()

    fun getCurrencies() : List<Currency>{
        var arr = mutableListOf<Currency>()
        val elements = xmlService.getElements(url,"Currency")
        for (element in elements){
            val currencyCode = element.attr("CurrencyCode")
            val unit = element.getElementsByTag("Unit").text()
            val name = element.getElementsByTag("Isim").text()
            val currencyName = element.getElementsByTag("CurrencyName").text()
            val forexBuying = element.getElementsByTag("ForexBuying").text()
            val forexSelling = element.getElementsByTag("ForexSelling").text()
            val banknoteBuying = element.getElementsByTag("BanknoteBuying").text()
            val banknoteSelling = element.getElementsByTag("BanknoteSelling").text()
            val currency = Currency(currencyCode,unit,name,currencyName,forexBuying,forexSelling,banknoteBuying,banknoteSelling)
            arr.add(currency)
        }
        return arr
    }

    fun getDate() : String{
        val elements = XmlService.elements
        if(elements != null){
            return elements[0].parent()?.attr("Tarih").toString()
        }
        return ""
    }

}
package com.example.odev_5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.odev_5.databinding.ActivityMainBinding
import com.example.odev_5.models.Currency
import com.example.odev_5.services.CurrencyService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var currencies = listOf<Currency>()
    private val currencyNames = mutableListOf<String>()
    private val currencyService = CurrencyService()
    private var date : String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = ContextCompat.getColor(this,R.color.background)

        val run = Runnable {
            currencies = currencyService.getCurrencies()
            date = currencyService.getDate()

            updateUI()
        }
        Thread(run).start()
    }

    private fun updateUI(){
        runOnUiThread {
            if(currencies.isNotEmpty()){
                for(currency in currencies){
                    currencyNames.add(currency.name)
                }
                binding.txtDate.visibility = View.VISIBLE
                binding.txtDate.text = date
                val adapter = ArrayAdapter(this,R.layout.list_item,currencyNames)
                binding.txtCurrencyName.setAdapter(adapter)
                binding.txtCurrencyName.setOnClickListener {
                    binding.textInputLayoutCurrencyName.isHintEnabled = false
                }
                binding.txtCurrencyName.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    fillComponents(currencies[position])
                }
            }else{
                binding.txtCurrencyName.visibility = View.GONE
            }
        }
    }

    private fun fillComponents(currency : Currency){
        binding.tvCurrencyCode.text = currency.currencyCode
        when(currency.forexBuying){
            null, "" -> "???"
            else -> binding.tvForexBuy.text = currency.forexBuying
        }
        when(currency.forexSelling){
            null, "" -> "???"
            else -> binding.tvForexSell.text = currency.forexSelling
        }
        when(currency.banknoteBuying){
            null, "" -> "???"
            else -> binding.tvBanknoteBuy.text = currency.banknoteBuying
        }
        when(currency.banknoteSelling){
            null, "" -> "???"
            else -> binding.tvBanknoteSell.text = currency.banknoteSelling
        }
        binding.tvCurrencyCodeForBankNote.text = currency.currencyCode
    }

}
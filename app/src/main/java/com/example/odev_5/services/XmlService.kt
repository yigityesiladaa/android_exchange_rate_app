package com.example.odev_5.services

import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements

class XmlService{

    companion object{
         var elements : Elements? = null
    }

     fun getElements(url : String, tagName : String) : Elements {
        val doc : Document = Jsoup.connect(url).timeout(15000).ignoreContentType(true).get()
        elements =  doc.getElementsByTag(tagName)
        return elements as Elements
    }
}
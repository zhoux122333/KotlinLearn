package com.zx.kotlin_android.acvtivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Toast
import com.zx.kotlin_android.R
import com.zx.kotlin_android.adapter.RecyclerAdapter
import com.zx.kotlin_android.http.Request
import org.jetbrains.anko.*

class MainActivity1 : AppCompatActivity() {
    private val items = listOf<String>(
            "Mon 6/23 - Sunny - 31/17",
            "Tue 6/24 - Sunny - 32/15",
            "Wed 6/25 - Rainy - 26/11",
            "Thurs 6/26 - Sunny - 30/18",
            "Fri 6/27 - Sunny - 35/16"
            )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        val forecastList:RecyclerView = find(R.id.rv) //Anko 暂时不用
//        val forecastList = findViewById(R.id.tv) as RecyclerView //老版本

        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyclerAdapter(items)
        toast("Anko")
        doAsync {
            Log.d(javaClass.simpleName,"logAsync")
            Request("https://www.baidu.com").run()
            uiThread { longToast("Request performed") }
        }
    }



//
//    fun toast(message: String, length: Int = Toast.LENGTH_SHORT){
//        Toast.makeText(this,message,length).show()
//    }


}

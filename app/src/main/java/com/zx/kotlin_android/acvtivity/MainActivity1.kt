package com.zx.kotlin_android.acvtivity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import com.zx.kotlin_android.R
import com.zx.kotlin_android.adapter.RecyclerAdapter
import com.zx.kotlin_android.app.App
import com.zx.kotlin_android.db.helper.ForecastDBHelper
import com.zx.kotlin_android.domain.RequestForecastCommand
import com.zx.kotlin_android.domain.model.Forecast
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
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
//        App.instance
//        val recyclerView = findViewById<RecyclerView>(R.id.rv)
        rv.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = RecyclerAdapter(items)
//        toast("Anko")
        doAsync {
            Log.d(javaClass.simpleName, "logAsync")

            val result = RequestForecastCommand(94043).execute()
//            Request("https://www.baidu.com").run()
            uiThread {
                //                recyclerView.adapter = RecyclerAdapter(result) { forecast -> toast(forecast.date)}
                rv.adapter = RecyclerAdapter(result) { toast(it.date.toString()) }

            }
        }

        val dbHelper1 = ForecastDBHelper()
    }
//
//    fun toast(message: String, length: Int = Toast.LENGTH_SHORT){
//        Toast.makeText(this,message,length).show()
//    }


}

package com.example.custalarmable

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast


class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val listAdapter = ListAdapter((1..5).map { AlarmItem() }.toMutableList())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.setDisplayShowTitleEnabled(false)

        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = listAdapter

        val swipeHandler = object : SwipeToDeleteCallback(this) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as ListAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)

        addItemBtn.setOnClickListener(this)
        jump.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id){
            R.id.addItemBtn -> {
                var intent = Intent(this, AlarmSetting::class.java)
                startActivity(intent)
                var testAlarm = AlarmItem()
                testAlarm.alarmEnable = true
                testAlarm.alarmType = "work"
                testAlarm.alarmName = "CS 465"
                listAdapter.addItem(testAlarm)
            }
            R.id.jump -> {
                var intent = Intent(this, Sleep::class.java)
                startActivity(intent)
            }

        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId){
            R.id.statistics -> {
                val intent = Intent(this, Analysis::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}

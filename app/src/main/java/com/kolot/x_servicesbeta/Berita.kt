package com.kolot.x_servicesbeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.kolot.x_servicesbeta.adapter.CardViewMyDataAdapter
import com.kolot.x_servicesbeta.adapter.GridMyDataAdapter
import com.kolot.x_servicesbeta.adapter.ListMyDataAdapter
import com.kolot.x_servicesbeta.databinding.ActivityBeritaBinding
import com.kolot.x_servicesbeta.databinding.ActivityPengaturanBinding
import kotlinx.android.synthetic.main.activity_berita.*

class Berita : AppCompatActivity() {
    private val list = ArrayList<MyData>()
    private lateinit var binding: ActivityBeritaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBeritaBinding.inflate(layoutInflater)
        setContentView(binding.root)
        rv_mydata.setHasFixedSize(true)
        list.addAll(getListMyDatas())
        showRecyclerList()
    }
    fun getListMyDatas(): ArrayList<MyData> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.getStringArray(R.array.data_photo)
        val listMyData = ArrayList<MyData>()
        for (position in dataName.indices) {
            val myData = MyData(
                dataName[position],
                dataDescription[position],
                dataPhoto[position]
            )
            listMyData.add(myData)
        }
        return listMyData
    }

    private fun showRecyclerList() {
        rv_mydata.layoutManager = LinearLayoutManager(this)
        val listMyDataAdapter = ListMyDataAdapter(list,this@Berita)
        rv_mydata.adapter = listMyDataAdapter
    }
    private fun showRecyclerGrid() {
        rv_mydata.layoutManager = GridLayoutManager(this, 2)
        val gridMyDataAdapter = GridMyDataAdapter(list,this@Berita)
        rv_mydata.adapter = gridMyDataAdapter
    }

    private fun showRecyclerCardView() {
        rv_mydata.layoutManager = LinearLayoutManager(this)
        val cardViewMyDataAdapter = CardViewMyDataAdapter (list,this@Berita)
        rv_mydata.adapter = cardViewMyDataAdapter
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }
    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.action_list -> {
                showRecyclerList()
            }
            R.id.action_grid -> {
                showRecyclerGrid()
            }
            R.id.action_cardview -> {
                showRecyclerCardView()
            }
        }
    }
}
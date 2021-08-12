package com.kolot.x_servicesbeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.kolot.x_servicesbeta.databinding.ActivityPengaturanBinding
import com.kolot.x_servicesbeta.databinding.ActivityTentangBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_tentang.*
import kotlinx.android.synthetic.main.fragment_tentang_kami.*

class Tentang : AppCompatActivity() {
    private lateinit var binding: ActivityTentangBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTentangBinding.inflate(layoutInflater)
        setContentView(binding.root)
        maps.setOnClickListener{
            val intent = Intent(this, MapsActivity::class.java)
            startActivity(intent)
        }
        val mFragmentManager = supportFragmentManager
        val mFirstFragment = TentangKami()
            mFragmentManager
                .beginTransaction()
                .add(R.id.frame_container, mFirstFragment, TentangKami::class.java.simpleName)
                .commit()
        }
    }


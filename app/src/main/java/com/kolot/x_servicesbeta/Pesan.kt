package com.kolot.x_servicesbeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kolot.x_servicesbeta.databinding.ActivityPengaturanBinding
import com.kolot.x_servicesbeta.databinding.ActivityPesanBinding

class Pesan : AppCompatActivity() {
    private lateinit var binding: ActivityPesanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPesanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}
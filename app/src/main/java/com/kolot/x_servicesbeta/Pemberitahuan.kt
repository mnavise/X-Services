package com.kolot.x_servicesbeta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kolot.x_servicesbeta.databinding.ActivityBeritaBinding
import com.kolot.x_servicesbeta.databinding.ActivityPemberitahuanBinding

class Pemberitahuan : AppCompatActivity() {
    private lateinit var binding: ActivityPemberitahuanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPemberitahuanBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}
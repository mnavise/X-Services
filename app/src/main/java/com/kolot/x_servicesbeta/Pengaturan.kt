package com.kolot.x_servicesbeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.kolot.x_servicesbeta.databinding.ActivityPengaturanBinding

class Pengaturan : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityPengaturanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengaturanBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.BtnTentang.setOnClickListener(this)
        binding.btnPengaturanUser.setOnClickListener(this)
    }
    override fun onClick(v: View) {
        when (v.id) {

            R.id.BtnTentang -> {
                val intent = Intent(this@Pengaturan, Tentang::class.java)
                startActivity(intent)
            }
            R.id.btnPengaturanUser -> {
                val intent = Intent(this@Pengaturan, PengaturanUserActivity::class.java)
                startActivity(intent)
            }
        }
    }
}
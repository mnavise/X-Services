package com.kolot.x_servicesbeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.bantuan.REQUEST_ADD
import com.kolot.x_servicesbeta.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        val currentUser = auth.currentUser

        binding.BtnPengaturan.setOnClickListener(this)
        binding.BtnBerita.setOnClickListener(this)
        binding.BtnBuat.setOnClickListener(this)
        binding.BtnExit.setOnClickListener(this)
        binding.BtnCek.setOnClickListener(this)

        if (currentUser == null) {
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser == null) {
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.BtnPengaturan -> {
                val intent = Intent(this@MainActivity, Pengaturan::class.java)
                startActivity(intent)
            }
            R.id.BtnExit -> {
                this@MainActivity.finish()
                exitProcess(0)
            }
            R.id.BtnBerita -> {
                val intent = Intent(this@MainActivity, Berita::class.java)
                startActivity(intent)
            }
            R.id.BtnBuat -> {
                val intent = Intent(this@MainActivity, BuatPesanan::class.java)
                startActivityForResult(intent, REQUEST_ADD)
            }
            R.id.BtnCek -> {
                val intent = Intent(this@MainActivity, CekPesanan::class.java)
                startActivity(intent)
            }
        }
    }

}

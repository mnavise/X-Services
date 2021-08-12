package com.kolot.x_servicesbeta

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.adapter.PesananAdapter
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.bantuan.REQUEST_UPDATE
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.bantuan.RESULT_DELETE
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.bantuan.RESULT_UPDATE
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.data.Pesanan
import com.kolot.x_servicesbeta.databinding.ActivityCekPesananBinding
import kotlinx.android.synthetic.main.activity_cek_pesanan.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class CekPesanan : AppCompatActivity() {
    private lateinit var adapter: PesananAdapter
    private lateinit var binding: ActivityCekPesananBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCekPesananBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firestore = Firebase.firestore
        auth = Firebase.auth
        supportActionBar?.title = "Cek Pesanan"
        binding.rvQuotes.layoutManager = LinearLayoutManager(this)
        binding.rvQuotes.setHasFixedSize(true)
        adapter = PesananAdapter(this)
        loadQuotes()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        loadQuotes()
    }

    private fun loadQuotes() {
        GlobalScope.launch(Dispatchers.Main) {
            progressbar.visibility = View.VISIBLE
            val quotesList = ArrayList<Pesanan>()
            val currentUser = auth.currentUser
            firestore.collection("pesanan")
                    .whereEqualTo("uid", currentUser?.uid)
                    .get()
                    .addOnSuccessListener { result ->
                        progressbar.visibility = View.INVISIBLE
                        for (document in result) {
                            val id = document.id
                            val title = document.get("title").toString()
                            val description = document.get("description").toString()
                            val category = document.get("category").toString()
                            val date = document.get("date") as com.google.firebase.Timestamp
                            quotesList.add(Pesanan(id, title, description, category, date))
                        }
                        if (quotesList.size > 0) {
                            binding.rvQuotes.adapter = adapter
                            adapter.listQuotes = quotesList
                        } else {
                            adapter.listQuotes.clear()
                            binding.rvQuotes?.adapter?.notifyDataSetChanged()
                            showSnackbarMessage("Tidak ada pesanan service")
                        }
                    }
                    .addOnFailureListener { exception ->
                        progressbar.visibility = View.INVISIBLE
                        Toast.makeText(
                                this@CekPesanan, "Error adding document", Toast.LENGTH_SHORT
                        ).show()
                    }
        }
    }
    private fun showSnackbarMessage(message: String) {
        Snackbar.make(binding.rvQuotes, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            when (requestCode) {
                REQUEST_UPDATE ->
                    when (resultCode) {
                        RESULT_UPDATE -> {
                            loadQuotes()
                            showSnackbarMessage("Pesanan berhasil diperbarui")
                        }
                        RESULT_DELETE -> {
                            loadQuotes()
                            showSnackbarMessage("Pesanan berhasil dihapus")
                        }
                    }
            }
        }
    }
}
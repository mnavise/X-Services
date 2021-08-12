package com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kolot.x_servicesbeta.BuatPesanan
import com.kolot.x_servicesbeta.R
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.bantuan.EXTRA_POSITION
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.bantuan.EXTRA_QUOTE
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.bantuan.REQUEST_UPDATE
import com.kolot.x_servicesbeta.com.kolot.x_servicesbeta.data.Pesanan
import com.kolot.x_servicesbeta.databinding.ItemPesananBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class PesananAdapter(private val activity: Activity): RecyclerView.Adapter<PesananAdapter.QuoteViewHolder>() {
    var listQuotes = ArrayList<Pesanan>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pesanan, parent, false)
        return QuoteViewHolder(view)
    }

    override fun getItemCount(): Int = this.listQuotes.size
    override fun onBindViewHolder(holder: QuoteViewHolder, position: Int) {
        holder.bind(listQuotes[position],position)
    }

    inner class QuoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemPesananBinding.bind(itemView)
        fun bind(quote: Pesanan, position: Int) {
            binding.tvItemTitle.text = quote.title
            binding.tvItemCategory.text = quote.category
            val timestamp = quote.date as com.google.firebase.Timestamp
            val milliseconds = timestamp.seconds * 1000 + timestamp.nanoseconds / 1000000
            val sdf = SimpleDateFormat("dd/MMM/yyyy, HH:mm")
            val netDate = Date(milliseconds)
            val date = sdf.format(netDate).toString()
            binding.tvItemDate.text = date
            binding.tvItemDescription.text = quote.description

            binding.cvItemQuote.setOnClickListener{
                val intent = Intent(activity, BuatPesanan::class.java)
                intent.putExtra(EXTRA_POSITION, position)
                intent.putExtra(EXTRA_QUOTE, quote)
                activity.startActivityForResult(intent, REQUEST_UPDATE)
            }
        }
    }
}
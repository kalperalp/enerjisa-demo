package com.demo.enerjisa.ui.Adapters

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.demo.enerjisa.R
import com.demo.enerjisa.base.BaseViewHolder
import com.demo.enerjisa.data.model.Invoice
import kotlinx.android.synthetic.main.invoice_row.view.*

class InvoicesAdapter(
    private val context: Context,
    private val items: List<Invoice>,
    private val itemClickListener: InvoicesAdapter.OnInvoiceClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnInvoiceClickListener {

        fun onInvoiceClick(invoice: Invoice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return InvoicesViewHolder(
            LayoutInflater.from(context).inflate(R.layout.invoice_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is InvoicesViewHolder -> holder.bind(items[position], position)
        }
    }

    inner class InvoicesViewHolder(itemView: View) : BaseViewHolder<Invoice>(itemView) {
        override fun bind(item: Invoice, position: Int) {

            itemView.tv_dueDate.text = "DueDate: " + item.dueDate
            itemView.tv_amount.text = "Amount: " + item.amount

            itemView.setOnClickListener { itemClickListener.onInvoiceClick(item) }
            itemView.img_pay.setOnClickListener { showDialog("Due Date: "+item.dueDate, context) }
            itemView.img_document.setOnClickListener { showDialog("Document Number: "+item.documentNumber,context) }
        }
    }

    private fun showDialog(title: String,context: Context) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.custom_dialog)
        val body = dialog.findViewById(R.id.tvBody) as TextView
        body.text = title
        val yesBtn = dialog.findViewById(R.id.btn_yes) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }


}
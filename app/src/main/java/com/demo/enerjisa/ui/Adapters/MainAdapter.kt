package com.demo.enerjisa.ui.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.enerjisa.R
import com.demo.enerjisa.base.BaseViewHolder
import com.demo.enerjisa.data.model.Company
import kotlinx.android.synthetic.main.company_row.view.*

class MainAdapter(
    private val context: Context,
    private val items: List<Company>,
    private val itemClickListener: OnCompanyClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    interface OnCompanyClickListener {

        fun onCompanyClick(company : Company)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(
            LayoutInflater.from(context).inflate(R.layout.company_row, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(items[position], position)
        }
    }

    inner class MainViewHolder(itemView: View) : BaseViewHolder<Company>(itemView) {
        override fun bind(item: Company, position: Int) {
            itemView.tv_company.text = "Company: " + item.company
            itemView.tv_installationNumber.text = "Installation Number: " + item.installationNumber
            itemView.tv_contractAccountNumber.text = "Contract Account Number: " + item.contractAccountNumber
            itemView.tv_amount.text = "Amount: " + item.amount
            itemView.tv_address.text = "Address: " + item.address

            itemView.setOnClickListener { itemClickListener.onCompanyClick(item) }
        }
    }

}
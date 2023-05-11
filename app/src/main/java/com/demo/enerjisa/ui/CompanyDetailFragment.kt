package com.demo.enerjisa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.enerjisa.data.model.Company
import com.demo.enerjisa.data.model.Invoice
import com.demo.enerjisa.data.model.UserInvoices
import com.demo.enerjisa.databinding.FragmentCompanyDetailBinding
import com.demo.enerjisa.ui.Adapters.InvoicesAdapter
import kotlinx.android.synthetic.main.fragment_company_detail.*
import kotlinx.android.synthetic.main.fragment_main.*


class CompanyDetailFragment : Fragment(),InvoicesAdapter.OnInvoiceClickListener {

    private lateinit var company: Company
    private lateinit var userInvoices: UserInvoices
    private lateinit var invoices: List<Invoice>

    private var _binding: FragmentCompanyDetailBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let {data ->
                userInvoices = data.getParcelable<UserInvoices>("userInvoices")!!
                company = data.getParcelable<Company>("company")!!
        }
        invoices = userInvoices.invoices.filter { invoice -> company.installationNumber.equals(invoice.installationNumber) }.toList()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCompanyDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initView()
    }

    fun initView(){
        binding.companyTextView.text = "Company: " +company.company
        binding.installationNumberTextView.text ="Installation Number: " +company.installationNumber
        binding.contractAccountNumberTextView.text="Contract Account Number: " +company.contractAccountNumber
        binding.amountTextView.text="Amount: " + company.amount
        binding.addressTextView.text="Address: " + company.address
        binding.rvInvoices.adapter = InvoicesAdapter(requireContext(), invoices,this)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onInvoiceClick(invoice: Invoice) {

    }

    private fun initRecycler() {
        binding.rvInvoices.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rvInvoices.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

}
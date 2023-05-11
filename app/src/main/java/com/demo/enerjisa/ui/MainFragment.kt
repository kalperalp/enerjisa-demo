package com.demo.enerjisa.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.demo.enerjisa.R
import com.demo.enerjisa.data.DataSource
import com.demo.enerjisa.data.model.Company
import com.demo.enerjisa.data.model.UserInvoices
import com.demo.enerjisa.domain.RepoImpl
import com.demo.enerjisa.ui.Adapters.MainAdapter
import com.demo.enerjisa.ui.viewmodel.MainViewModel
import com.demo.enerjisa.ui.viewmodel.VMFactory
import com.demo.enerjisa.vo.Resource
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment() : Fragment(), MainAdapter.OnCompanyClickListener {

    private lateinit var userInvoices: UserInvoices
    private val viewModel by activityViewModels<MainViewModel> {
        VMFactory(
            RepoImpl(DataSource())
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()

        viewModel.fetchUserInvoices.observe(viewLifecycleOwner, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    progresBar.visibility = View.VISIBLE
                }
                is Resource.Success -> {
                    userInvoices =result.data
                    progresBar.visibility = View.GONE
                    tv_total_price.text = "Tüm Borç: "+result.data.totalPrice
                    tv_total_price_count.text = "Tüm sözleşme hesaplarınıza ait "+result.data.totalPriceCount.toString()+ " adet veri bulunmaktadır."
                    rv_companies.adapter = MainAdapter(requireContext(), result.data.list, this)
                }
                is Resource.Failure -> {
                    progresBar.visibility = View.GONE
                    Toast.makeText(requireContext(), "Fallo al cargar datos", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }


    private fun initRecycler() {
        rv_companies.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_companies.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }


    override fun onCompanyClick(company: Company) {
        var bundle = Bundle()
        bundle.putParcelable("company", company)
        bundle.putParcelable("userInvoices", userInvoices)

        findNavController().navigate(R.id.companyDetailFragment, bundle)
    }


}


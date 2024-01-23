package com.example.admdelivery.Activitys.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admdelivery.Activitys.Adapter.ProdutoAdapter
import com.example.admdelivery.Activitys.DataSource.DB
import com.example.admdelivery.Activitys.Model.Produto

import com.example.admdelivery.R
import com.example.admdelivery.databinding.FragmentProdutosBinding
import javax.sql.DataSource


class ProdutosFragment : Fragment() {
    private lateinit var binding :FragmentProdutosBinding
    private lateinit var produtoAdapter: ProdutoAdapter
    private val listaProdutos:MutableList<Produto> = mutableListOf()
    private val db = DB()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProdutosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycleViewProdutos = binding.recycleProdutos
        recycleViewProdutos.layoutManager = LinearLayoutManager(context)
        produtoAdapter = ProdutoAdapter(requireContext(),listaProdutos)
        recycleViewProdutos.setHasFixedSize(true)
        recycleViewProdutos.adapter = produtoAdapter
        db.getProdutos(listaProdutos,produtoAdapter)





    }

}
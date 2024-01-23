package com.example.admdelivery.Activitys.fragments

import Pedido
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.admdelivery.Activitys.Adapter.PedidoAdapter
import com.example.admdelivery.Activitys.DataSource.DB

import com.example.admdelivery.R
import com.example.admdelivery.databinding.FragmentPedidosBinding


class PedidosFragment : Fragment() {
    private lateinit var binding: FragmentPedidosBinding
    private lateinit var pedidosAdapter: PedidoAdapter
    private val listapedidos: MutableList<Pedido> = mutableListOf()
    private val db = DB()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentPedidosBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val recyclerviewePedidos = binding.entregasRecycle

        recyclerviewePedidos.layoutManager = LinearLayoutManager(context)
        recyclerviewePedidos.setHasFixedSize(true)
        pedidosAdapter = PedidoAdapter(requireContext(),listapedidos)
        recyclerviewePedidos.adapter = pedidosAdapter
        if (listapedidos != null) {
            db.getPedidos(listapedidos,pedidosAdapter)


        }
        Log.d("Pedido", "Pedido fromSnapshot FRAGMENT exibiçao: $listapedidos ...")





    }


}
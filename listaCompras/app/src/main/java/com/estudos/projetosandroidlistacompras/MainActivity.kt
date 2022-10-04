package com.estudos.projetosandroidlistacompras

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val produtosAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1)

        val listProdutos = findViewById<ListView>(R.id.list_view_produtos)
        listProdutos.adapter = produtosAdapter

        val botaoAdicionar  = findViewById<Button>(R.id.btn_inserir)
        val text_produto = findViewById<TextView>(R.id.txt_produto)

        botaoAdicionar.setOnClickListener{
            val produto = text_produto.text.toString()
            if(produto.isNotEmpty()){
                produtosAdapter.add(produto)
                text_produto.text = ""
            }else{
                text_produto.error = "Prrencha um valor"
            }

        }


        listProdutos.setOnItemLongClickListener{ adapterView: AdapterView<*>, view: View, position: Int, id: Long ->

            //buscando o item clicado
            val item = produtosAdapter.getItem(position)

            //removendo o item clicado da lista
            produtosAdapter.remove(item)

            //retorno indicando que o click foi realizado com sucesso
            true
        }
    }
}
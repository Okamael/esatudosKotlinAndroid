package com.estudos.projetosandroidlistacompras

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.NumberFormat
import java.util.*


class MainActivity : AppCompatActivity() {

    val COD_IMAGE =101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val produtoAdapter = ProdutoAdapter(this)

        produtoAdapter.addAll(produtosGlobal)

        list_view_produtos.adapter = produtoAdapter

        btn_adicionar.setOnClickListener{
            val intent = Intent(this,CadastroActivity::class.java)

            startActivity(intent)
        }



        list_view_produtos.setOnItemLongClickListener{ adapterView: AdapterView<*>, view: View, position: Int, id: Long ->

            //buscando o item clicado
            val item = produtoAdapter.getItem(position)

            //removendo o item clicado da lista
            produtoAdapter.remove(item)

            //retorno indicando que o click foi realizado com sucesso
            true
        }


    }
    override fun onResume(){
        super.onResume()
        val  adapter = list_view_produtos.adapter as ProdutoAdapter
        adapter.clear()
        adapter.addAll(produtosGlobal)

        val soma = produtosGlobal.sumByDouble { it.valor * it.quantidade }

        val f= NumberFormat.getCurrencyInstance(Locale("pt","br"))
        txt_total.text = "TOTAL:${f.format(soma)}"
    }

    fun abrirGaleria(){
        //definindo a ação de conteúdo

        val intent = Intent(Intent(Intent.ACTION_GET_CONTENT))

        //definindo filtro para imagem
        intent.type="image/*"

        //iniciando a acitivty com resultado
        startActivityForResult(Intent.createChooser(intent,"Selecione uma imagem"), COD_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == COD_IMAGE && resultCode == Activity.RESULT_OK){
            if(data != null){
                //neste ponto acessamos a imagem escolhida pela variável "data"
            }
        }
    }
}
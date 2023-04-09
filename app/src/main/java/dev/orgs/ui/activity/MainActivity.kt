package dev.orgs.ui.activity

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import dev.orgs.R
import dev.orgs.model.Produto
import dev.orgs.ui.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Bem vindo(a) ao Orgs", Toast.LENGTH_SHORT).show();

        setContentView(R.layout.activity_main);

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);

        recyclerView.adapter = ListaProdutosAdapter(context = this, produtos = listOf(
            Produto(nome =  "Produto Teste 1", descricao = "Descriçao teste produto 1", valor = BigDecimal("10.99")),
            Produto(nome =  "Produto Teste 2", descricao = "Descriçao teste produto 2", valor = BigDecimal("19.99")),
            Produto(nome =  "Produto Teste 3", descricao = "Descriçao teste produto 2", valor = BigDecimal("29.99"))
        ))
    }
}
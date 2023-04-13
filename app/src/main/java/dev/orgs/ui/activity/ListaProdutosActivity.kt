package dev.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.orgs.R
import dev.orgs.dao.ProdutoDAO
import dev.orgs.ui.recyclerview.adapter.ListaProdutosAdapter


class ListaProdutosActivity : AppCompatActivity(R.layout.activity_lista_produtos) {

    private val produtosDao = ProdutoDAO();
    private val adapter =
        ListaProdutosAdapter(context = this, produtos = produtosDao.buscarTodos());

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Bem vindo(a) ao Orgs", Toast.LENGTH_SHORT).show();

        configuraRecyclerView();
        configuraFab();
    }

    override fun onResume() {
        super.onResume();
        adapter.atualiza(produtosDao.buscarTodos());
    }

    private fun configuraRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView);


        recyclerView.adapter = adapter;

    }

    private fun configuraFab() {
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton);
        fab.setOnClickListener {
            val intent = Intent(this, FormularioProdutoActivity::class.java);
            startActivity(intent);
        }
    }
}
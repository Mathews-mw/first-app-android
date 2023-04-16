package dev.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dev.orgs.R
import dev.orgs.dao.ProdutoDAO
import dev.orgs.databinding.ActivityListaProdutosBinding
import dev.orgs.ui.recyclerview.adapter.ListaProdutosAdapter


class ListaProdutosActivity : AppCompatActivity() {

    private val produtosDao = ProdutoDAO();
    private val adapter =
        ListaProdutosAdapter(context = this, produtos = produtosDao.buscarTodos());

    private val biding by lazy {
        ActivityListaProdutosBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, "Bem vindo(a) ao Orgs", Toast.LENGTH_SHORT).show();
        setContentView(biding.root);

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
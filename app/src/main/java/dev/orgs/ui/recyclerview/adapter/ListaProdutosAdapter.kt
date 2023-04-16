package dev.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.orgs.R
import dev.orgs.databinding.ProdutoItemBinding
import dev.orgs.extensions.tentaCarregarImagem
import dev.orgs.model.Produto
import java.text.NumberFormat
import java.util.*

class ListaProdutosAdapter(private val context: Context, produtos: List<Produto>) :
    RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val produtos = produtos.toMutableList();

    class ViewHolder(private val biding: ProdutoItemBinding) :
        RecyclerView.ViewHolder(biding.root) {
        fun vincula(produto: Produto) {
            val nome = biding.produtoItemNome;
            nome.text = produto.nome;

            val descricao = biding.produtoItemDescricao;
            descricao.text = produto.descricao;

            val valor = biding.produtoItemValor;
            val currencyInstance = NumberFormat.getCurrencyInstance(Locale("pt", "br"));
            val valorFormatted = currencyInstance.format(produto.valor);

            valor.text = valorFormatted;

            val visibilidade = if (produto.imagem != null) {
                View.VISIBLE;
            } else {
                View.GONE;
            }

            biding.imageView.visibility = visibilidade;

            biding.imageView.tentaCarregarImagem(produto.imagem);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context);
        val biding = ProdutoItemBinding.inflate(inflater, parent, false);

        return ViewHolder(biding);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = produtos[position];
        holder.vincula(produto)
    }

    override fun getItemCount(): Int = produtos.size;
    fun atualiza(produtos: List<Produto>) {
        this.produtos.clear();
        this.produtos.addAll(produtos);
        notifyDataSetChanged();
    }
}

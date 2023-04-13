package dev.orgs.dao

import dev.orgs.model.Produto

class ProdutoDAO {
    fun adiciona(produto: Produto) {
        produtos.add(produto);
    }

    fun buscarTodos(): List<Produto> {
        return produtos.toList();
    }

    companion object {
        private val produtos = mutableListOf<Produto>();
    }
}
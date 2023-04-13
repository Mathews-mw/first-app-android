package dev.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import dev.orgs.R
import dev.orgs.dao.ProdutoDAO
import dev.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        configuraBotaoSalvar();
    }
    private fun configuraBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.botao_salvar);
        val produtoDao = ProdutoDAO();

        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            produtoDao.adiciona(produtoNovo);
            finish();
        }
    }
    private fun criaProduto(): Produto {
        val campoNome = findViewById<EditText>(R.id.activity_formulario_produto_nome);
        val nome = campoNome.text.toString();

        val campoDescricao = findViewById<EditText>(R.id.activity_formulario_produto_descricao);
        val descricao = campoDescricao.text.toString();

        val campoValor = findViewById<EditText>(R.id.activity_formulario_produto_valor);
        val valorRaw = campoValor.text.toString();
        val valorParse = if (valorRaw.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorRaw);
        }

        val produtoNovo = Produto(nome, descricao, valorParse);
        return produtoNovo
    }
}
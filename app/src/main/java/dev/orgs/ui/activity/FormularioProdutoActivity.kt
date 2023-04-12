package dev.orgs.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import dev.orgs.R
import dev.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);

        val botaoSalvar = findViewById<Button>(R.id.botao_salvar);

        botaoSalvar.setOnClickListener {
            val campoNome = findViewById<EditText>(R.id.nome);
            val nome = campoNome.text.toString();

            val campoDescricao = findViewById<EditText>(R.id.descricao);
            val descricao = campoDescricao.text.toString();

            val campoValor = findViewById<EditText>(R.id.valor);
            val valorRaw = campoValor.text.toString();
            val valorParse = if(valorRaw.isBlank()) {
                BigDecimal.ZERO
            } else {
                BigDecimal(valorRaw);
            }

            val produtoNovo = Produto(nome, descricao, valorParse);

            Log.i("FormProduto", "onCreate: $produtoNovo");
        }
    }
}
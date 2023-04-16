package dev.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import coil.load
import dev.orgs.R
import dev.orgs.dao.ProdutoDAO
import dev.orgs.databinding.ActivityFormularioProdutoBinding
import dev.orgs.databinding.FormularioImagemBinding
import dev.orgs.extensions.tentaCarregarImagem
import dev.orgs.model.Produto
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState);
        setContentView(binding.root);
        configuraBotaoSalvar();

        binding.activityFormularioProdutoImage.setOnClickListener {
            val bindingFormularioImagem = FormularioImagemBinding.inflate(layoutInflater);
            bindingFormularioImagem.fomularioImagemBtnCarregar.setOnClickListener {
                val url = bindingFormularioImagem.formularioImagemUrl.text.toString();
                bindingFormularioImagem.formularioImagemImageView.tentaCarregarImagem(url);
            }

            AlertDialog.Builder(this)
                .setView(bindingFormularioImagem.root)
                .setPositiveButton("Confirmar") { _, _ ->
                    url = bindingFormularioImagem.formularioImagemUrl.text.toString();
                    binding.activityFormularioProdutoImage.tentaCarregarImagem(url);
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show();
        }
    }

    private fun configuraBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar;
        val produtoDao = ProdutoDAO();

        botaoSalvar.setOnClickListener {
            val produtoNovo = criaProduto()
            produtoDao.adiciona(produtoNovo);
            finish();
        }
    }

    private fun criaProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome;
        val nome = campoNome.text.toString();

        val campoDescricao = binding.activityFormularioProdutoDescricao;
        val descricao = campoDescricao.text.toString();

        val campoValor = binding.activityFormularioProdutoValor;
        val valorRaw = campoValor.text.toString();
        val valorParse = if (valorRaw.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valorRaw);
        }

        val produtoNovo = Produto(nome, descricao, valorParse, url);
        return produtoNovo;
    }
}


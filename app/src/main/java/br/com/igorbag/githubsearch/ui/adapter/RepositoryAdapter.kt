package br.com.igorbag.githubsearch.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView // -> IMPORT CORRIGIDO
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.domain.Repository

class RepositoryAdapter(
    private val repositories: List<Repository>,
    // Boa prática: receber os listeners no construtor
    private val onItemClick: (Repository) -> Unit,
    private val onShareClick: (Repository) -> Unit
) : RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    // Cria uma nova view (chamado pelo layout manager)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.repository_item, parent, false)
        return ViewHolder(view)
    }

    // Pega o conteúdo da view e preenche com os dados do item da lista
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repo = repositories[position]

        // Preenche o TextView com o nome do repositório
        holder.nomeRepositorio.text = repo.name

        // Configura o listener para o clique no item inteiro
        holder.itemView.setOnClickListener {
            onItemClick(repo)
        }

        // Configura o listener para o clique no botão de compartilhar
        holder.btnShare.setOnClickListener {
            onShareClick(repo)
        }
    }

    // Pega a quantidade de repositórios da lista
    // @TODO 9
    override fun getItemCount(): Int = repositories.size

    // Classe que armazena as referências das views de cada item da lista
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // @TODO 10
        val nomeRepositorio: TextView
        val btnShare: ImageView // Supondo que seja uma ImageView

        init {
            // Encontra as views dentro do layout do item
            nomeRepositorio = view.findViewById(R.id.tv_preco)
            btnShare = view.findViewById(R.id.iv_favorite)
        }
    }
}

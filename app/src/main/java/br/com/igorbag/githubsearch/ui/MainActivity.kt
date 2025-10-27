package br.com.igorbag.githubsearch.ui

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.WindowInsetsController
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.igorbag.githubsearch.R
import br.com.igorbag.githubsearch.data.GitHubService
import br.com.igorbag.githubsearch.domain.Repository
import br.com.igorbag.githubsearch.ui.adapter.RepositoryAdapter
import com.google.android.material.internal.ViewUtils.hideKeyboard
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var nomeUsuario: EditText
    lateinit var btnConfirmar: Button
    lateinit var listaRepositories: RecyclerView
    lateinit var githubApi: GitHubService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_main)

        setupRetrofit()
        setupView()
        setupListeners()
        showUserName()
    }

    // Metodo responsavel por realizar o setup da view e recuperar os Ids do layout
    fun setupView() {
        nomeUsuario = findViewById(R.id.et_nome_usuario)
        btnConfirmar = findViewById(R.id.btn_confirmar)
        listaRepositories = findViewById(R.id.rv_lista_repositories)
    }

    //metodo responsavel por configurar os listeners click da tela
    private fun setupListeners() {
        btnConfirmar.setOnClickListener {
            val user = nomeUsuario.text.toString()
            if (user.isNotEmpty()) {
                hideKeyboard()
                saveUserLocal(user)
                getAllReposByUserName()
            } else {
                Toast.makeText(this, "Por favor, digite um nome de usuário.", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    // salvar o usuario preenchido no EditText utilizando uma SharedPreferences
    private fun saveUserLocal(usuario: String) {
        val sharedPref = getSharedPreferences("github_prefs", Context.MODE_PRIVATE)
        with(sharedPref.edit()) {
            putString("saved_user", usuario)
            apply()
        }
    }

    private fun showUserName() {
        val sharedPref = getSharedPreferences("github_prefs", Context.MODE_PRIVATE)
        val savedUser = sharedPref.getString("saved_user", null)
        if (savedUser != null) {
            nomeUsuario.setText(savedUser)
            getAllReposByUserName()
        }
    }

    // Metodo responsavel por fazer a configuracao base do Retrofit
    fun setupRetrofit() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        githubApi = retrofit.create(GitHubService::class.java)
    }

    // Metodo responsavel por buscar todos os repositorios do usuario fornecido
    fun getAllReposByUserName() {
        val userName = nomeUsuario.text.toString()
        if (userName.isNotEmpty()) {
            val call = githubApi.getAllRepositoriesByUser(userName)

            call.enqueue(object : Callback<List<Repository>> {
                override fun onResponse(call: Call<List<Repository>>, response: Response<List<Repository>>) {
                    if (response.isSuccessful) {
                        response.body()?.let { repos ->
                            setupAdapter(repos)
                        }
                    } else {
                        Toast.makeText(applicationContext, "Usuário não encontrado.", Toast.LENGTH_SHORT).show()
                        setupAdapter(emptyList())
                    }
                }

                override fun onFailure(call: Call<List<Repository>>, t: Throwable) {
                    Toast.makeText(applicationContext, "Falha na requisição: ${t.message}", Toast.LENGTH_LONG).show()
                }
            })
        }
    }

    // Metodo responsavel por realizar a configuracao do adapter
    // -> CORRIGIDO AQUI
    fun setupAdapter(list: List<Repository>) {
        // Agora passamos as duas ações para o adapter
        val adapter = RepositoryAdapter(
            list,
            onItemClick = { repository ->
                // Ação para clique no item: abrir no navegador
                openBrowser(repository.htmlUrl)
            },
            onShareClick = { repository ->
                // Ação para clique no ícone de share: compartilhar o link
                shareRepositoryLink(repository.htmlUrl)
            }
        )

        // Define o adapter e o layout manager na RecyclerView
        listaRepositories.adapter = adapter
        listaRepositories.layoutManager = LinearLayoutManager(this)
    } // <- A CHAVE QUE FALTAVA ESTÁ AQUI

    // Metodo responsavel por compartilhar o link do repositorio selecionado
    fun shareRepositoryLink(urlRepository: String) {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, urlRepository)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        startActivity(shareIntent)
    }

    // Metodo responsavel por abrir o browser com o link informado do repositorio
    fun openBrowser(urlRepository: String) {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse(urlRepository)
            )
        )
    }
} // <- Chave de fechamento final da classe MainActivity

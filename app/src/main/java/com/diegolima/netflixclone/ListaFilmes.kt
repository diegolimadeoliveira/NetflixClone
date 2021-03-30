package com.diegolima.netflixclone

import com.diegolima.netflixclone.OnClick.OnItemClickListener
import com.diegolima.netflixclone.OnClick.addOnItemClickListener
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.diegolima.netflixclone.Adapter.FilmesAdapter
import com.diegolima.netflixclone.Modal.addFilmes
import com.diegolima.netflixclone.databinding.ActivityListaFilmesBinding
import com.google.firebase.auth.FirebaseAuth

class ListaFilmes : AppCompatActivity() {

    private lateinit var binding: ActivityListaFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListaFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recycler_filmes = binding.recyclerview
        recycler_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_filmes.layoutManager = GridLayoutManager(applicationContext, 3)

        recycler_filmes.addOnItemClickListener(object : OnItemClickListener {
            override fun onItemClicked(position: Int, view: View) {
                when {
                    position == 0 -> DetalhesFilmes()
                }
            }
        })
    }

    private fun DetalhesFilmes(){
        val intent = Intent(this, DetalhesFilmes::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_principal, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.deslogar -> {
                FirebaseAuth.getInstance().signOut()
                VoltarTelaLogin()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun VoltarTelaLogin() {
        val intent = Intent(this, FormLogin::class.java)
        startActivity(intent)
        finish()
    }
}
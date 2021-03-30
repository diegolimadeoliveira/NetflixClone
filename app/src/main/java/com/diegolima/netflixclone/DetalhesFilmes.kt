package com.diegolima.netflixclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.diegolima.netflixclone.Adapter.FilmesAdapter
import com.diegolima.netflixclone.Modal.addFilmes
import com.diegolima.netflixclone.databinding.ActivityDetalhesFilmesBinding
import com.squareup.picasso.Picasso

class DetalhesFilmes : AppCompatActivity() {

    private lateinit var binding: ActivityDetalhesFilmesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalhesFilmesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar!!.hide()
        Toobar()

        val recycler_outros_filmes = binding.recyclerOutrosFilmes
        recycler_outros_filmes.adapter = FilmesAdapter(addFilmes())
        recycler_outros_filmes.layoutManager = GridLayoutManager(applicationContext, 3)

        //carregando imagem
        val capaTheWitcher = "https://firebasestorage.googleapis.com/v0/b/netflix-clone-31446.appspot.com/o/video.jpg?alt=media&token=4eb9a0fe-8987-4c1b-a420-1f1b865944bb"
        Picasso.get().load(capaTheWitcher).fit().into(binding.capa)

        //carregando video
        binding.playVideo.setOnClickListener {
            val intent = Intent(this, Video::class.java)
            startActivity(intent)

        }

    }

    private fun Toobar() {
        val toobar_detalhes = binding.toobarDetalhes
        toobar_detalhes.setNavigationIcon(getDrawable(R.drawable.ic_voltar))
        toobar_detalhes.setNavigationOnClickListener {
            val intent = Intent(this, ListaFilmes::class.java)
            startActivity(intent)
            finish()
        }
    }
}
package tads.eaj.ufrn.exemplorecyclerview

import android.util.Log
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class NovoFrutaAdapter : RecyclerView.Adapter<NovoFrutaViewHolder>() {

    var frutas:List<Fruta> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NovoFrutaViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fruta_inflater, parent, false)
        val holder = NovoFrutaViewHolder(v)
        return holder
    }

    override fun onBindViewHolder(holder: NovoFrutaViewHolder, position: Int) {
        val frutaEscolhida = frutas.get(position)

        holder.nomeFruta.text = frutaEscolhida.nome
        holder.imgFruta.setImageResource(frutaEscolhida.img)
    }

    override fun getItemCount(): Int {
        return frutas.size
    }

}


class NovoFrutaViewHolder(v:View) : RecyclerView.ViewHolder(v){
    val nomeFruta = v.findViewById<TextView>(R.id.nomeFruta)
    val imgFruta = v.findViewById<ImageView>(R.id.imgFruta)

    init {
        imgFruta.setOnClickListener {
            imgFruta.setImageResource(R.mipmap.ic_launcher)
        }
    }
}
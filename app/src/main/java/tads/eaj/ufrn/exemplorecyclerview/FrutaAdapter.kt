package tads.eaj.ufrn.exemplorecyclerview

import android.content.Context
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater





class FrutaAdapter(var c:Context, var frutas:List<Fruta>) : RecyclerView.Adapter<FrutaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FrutaViewHolder {
        //Exitem 2 exemplos de layout para ser inflado nessse projeto. Teste os 3.

        //val view = LayoutInflater.from(c).inflate(R.layout.fruta_inflater, parent, false)
        val view = LayoutInflater.from(c).inflate(R.layout.fruta_card_inflater, parent, false);

        return FrutaViewHolder(view)
    }

    override fun getItemCount(): Int {
        return frutas.size
    }

    override fun onBindViewHolder(holder: FrutaViewHolder, position: Int) {

        val frutaescolhida = frutas[position]
        holder.textViewNome.text = frutaescolhida.nome
        holder.img.setImageResource(frutaescolhida.img)

        if (frutaescolhida.bitten) {
            holder.img.setImageResource(R.drawable.bitten)
        } else {
            holder.img.setImageResource(R.drawable.fruit)
        }
        holder.img.setOnClickListener{
            frutaescolhida.bitten = true
            notifyItemChanged(position)
        }
    }
}
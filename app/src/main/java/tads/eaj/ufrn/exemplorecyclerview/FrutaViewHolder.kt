package tads.eaj.ufrn.exemplorecyclerview

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class FrutaViewHolder(v: View) : RecyclerView.ViewHolder(v) {

    val textViewNome: TextView
    val img: ImageView

    init {
        textViewNome = v.findViewById(R.id.nomeFruta)
        img = v.findViewById(R.id.imgFruta)

        img.setOnClickListener {
            img.setImageResource(R.drawable.bitten)
        }

        /*
        Observação: Esse método está apenas trocando o estado da imagem na view do adapter,
        logo, ao ocorrer alguma ação de scroll essa atualização será perdida.
        Para que essa atualização seja permanente é preciso criar um atributo dentro da classe
        Fruta que guarde o estado da imagem. Ex. Boolean mordida;
         */
    }
}
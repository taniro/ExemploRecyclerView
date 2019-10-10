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
    }
}
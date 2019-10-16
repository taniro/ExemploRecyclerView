package tads.eaj.ufrn.exemplorecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import android.widget.Toast
import android.view.View
import androidx.recyclerview.widget.DefaultItemAnimator
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import jp.wasabeef.recyclerview.animators.LandingAnimator
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator


class MainActivity : AppCompatActivity() {

    val db:AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "frutas.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var f1 = Fruta ("Maça", R.drawable.fruit)
        var f2 = Fruta ("Maça", R.drawable.fruit)
        var f3 = Fruta ("Maça", R.drawable.fruit)

        with(db.frutaDao()){
            add(f1)
            add(f2)
            add(f3)
        }

        var listaFrutas:MutableList<Fruta> = db.frutaDao().listAll()

        var adapter = FrutaAdapter(this, listaFrutas)
        recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        recyclerview.layoutManager = layout

        recyclerview.addOnItemTouchListener(
            MyRecyclerViewClickListener(
                this@MainActivity,
                recyclerview,
                object : MyRecyclerViewClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(this@MainActivity, "Clique simples", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = listaFrutas[position]
                        listaFrutas.remove(removida)
                        recyclerview.adapter!!.notifyItemRemoved(position)
                        Toast.makeText(this@MainActivity, "Clique longo", Toast.LENGTH_SHORT)
                            .show()
                        val snack = Snackbar.make(
                            recyclerview.parent as View,"Removido",Snackbar.LENGTH_LONG )
                            .setAction("Cancelar") {
                                listaFrutas.add(position, removida)
                                recyclerview.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()
                    }
                })
        )

        recyclerview.itemAnimator = DefaultItemAnimator()
        //recyclerview.itemAnimator = LandingAnimator()
        //recyclerview.itemAnimator = FlipInTopXAnimator()
    }
}

package tads.eaj.ufrn.exemplorecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import android.widget.Toast
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import jp.wasabeef.recyclerview.animators.FlipInTopXAnimator
import jp.wasabeef.recyclerview.animators.LandingAnimator
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator
import tads.eaj.ufrn.exemplorecyclerview.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding

    val db:AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "frutas.sqlite")
            .allowMainThreadQueries()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)


        val f1 = Fruta ("Maça", R.drawable.fruit)
        val f2 = Fruta ("Pera", R.drawable.fruit)
        val f3 = Fruta ("Uva", R.drawable.fruit)

        with(db.frutaDao()){
            add(f1)
            add(f2)
            add(f3)
        }

        val listaFrutas:MutableList<Fruta> = db.frutaDao().listAll()

        //val adapter = FrutaAdapter(this, listaFrutas)

        val adapter = NovoFrutaAdapter()
        adapter.frutas = listaFrutas

        binding.recyclerview.adapter = adapter

        val layout = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //val layout = StaggeredGridLayoutManager(10, StaggeredGridLayoutManager.HORIZONTAL)

        binding.recyclerview.layoutManager = layout

        binding.recyclerview.addOnItemTouchListener(NovoRecyclerViewClickListener(this, binding.recyclerview,
            object : NovoRecyclerViewClickListener.onItemClickListener{
                override fun onItemClick(v: View, position: Int) {
                    Toast.makeText(this@MainActivity, "Clique simples", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onItemLongClick(v: View, position: Int) {
                    val removida = listaFrutas[position]
                    listaFrutas.remove(removida)
                    binding.recyclerview.adapter!!.notifyItemRemoved(position)
                    Toast.makeText(this@MainActivity, "Clique longo", Toast.LENGTH_SHORT)
                        .show()
                    val snack = Snackbar.make(
                        binding.recyclerview.parent as View,"Removido",Snackbar.LENGTH_LONG )
                        .setAction("Cancelar") {
                            listaFrutas.add(position, removida)
                            binding.recyclerview.adapter!!.notifyItemInserted(position)
                        }
                    snack.show()
                }
            }))

        /*
        binding.recyclerview.addOnItemTouchListener(
            MyRecyclerViewClickListener(
                this@MainActivity,
                binding.recyclerview,
                object : MyRecyclerViewClickListener.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int) {
                        Toast.makeText(this@MainActivity, "Clique simples", Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onItemLongClick(view: View, position: Int) {
                        val removida = listaFrutas[position]
                        listaFrutas.remove(removida)
                        binding.recyclerview.adapter!!.notifyItemRemoved(position)
                        Toast.makeText(this@MainActivity, "Clique longo", Toast.LENGTH_SHORT)
                            .show()
                        val snack = Snackbar.make(
                            binding.recyclerview.parent as View,"Removido",Snackbar.LENGTH_LONG )
                            .setAction("Cancelar") {
                                listaFrutas.add(position, removida)
                                binding.recyclerview.adapter!!.notifyItemInserted(position)
                            }
                        snack.show()
                    }
                })
        )

         */


        //binding.recyclerview.itemAnimator = DefaultItemAnimator()
        //binding.recyclerview.itemAnimator = LandingAnimator()
        //binding.recyclerview.itemAnimator = FlipInTopXAnimator()
    }
}

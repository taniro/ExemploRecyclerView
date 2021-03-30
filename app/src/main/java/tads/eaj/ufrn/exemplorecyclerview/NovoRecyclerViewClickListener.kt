package tads.eaj.ufrn.exemplorecyclerview

import android.content.Context
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class NovoRecyclerViewClickListener(val context: Context, val recyclerView: RecyclerView, val listener:onItemClickListener) : RecyclerView.OnItemTouchListener {

    var myGestureDetector:GestureDetector

    init {
        myGestureDetector = GestureDetector(context, object :
            GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    super.onSingleTapUp(e)
                    val childView = recyclerView.findChildViewUnder(e.x, e.y)
                    if (childView != null) {
                        listener.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
                        Log.i("Teste", "onSingleTapUp ")
                    }
                    return true
                }
                override fun onLongPress(e: MotionEvent) {
                    super.onLongPress(e)
                    val childView = recyclerView.findChildViewUnder(e.x, e.y)
                    if (childView != null) {
                        listener.onItemLongClick(childView,recyclerView.getChildAdapterPosition(childView))
                        Log.i("Teste", "onLongPress")
                    }
                }
            })
    }



    interface onItemClickListener{
        fun onItemClick(v: View, position:Int)
        fun onItemLongClick(v:View, position: Int)
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        myGestureDetector.onTouchEvent(e)
        return false
    }

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
    }
}
package com.sonukg.sonu_kumar_practicle_task.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sonukg.sonu_kumar_practicle_task.R
import com.sonukg.sonu_kumar_practicle_task.model.PhotosModel
import io.reactivex.subjects.PublishSubject
import io.reactivex.Observable

class PhotosAdapter(private val photosModel: List<PhotosModel>):
    RecyclerView.Adapter<PhotosAdapter.MyViewHolder>() {
    private val clickSubject = PublishSubject.create<String>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycleview_item_list, parent, false)
        return MyViewHolder(view)
    }
    val clickEvent: Observable<String> = clickSubject
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(photosModel[position])
        holder.apply {
            id.text = photosModel[position].id.toString()
            name.text = photosModel[position].title
            thumbnail.text = photosModel[position].thumbnailUrl
        }
    }

    override fun getItemCount(): Int =photosModel.size

   public inner class MyViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val id: TextView = itemView.findViewById(R.id.id_title)
        val name: TextView = itemView.findViewById(R.id.name_title)
        val thumbnail: TextView = itemView.findViewById(R.id.thumnail_title)

        init {
            itemView.setOnClickListener {
                clickSubject.onNext(photosModel[layoutPosition].toString())
            }
        }
        fun bind(item: PhotosModel) {
            name.text = item.title
            thumbnail.text=item.thumbnailUrl
        }
    }
}
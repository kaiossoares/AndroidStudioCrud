package com.example.frontend.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R
import com.example.frontend.activity.AlterActivity
import com.example.frontend.model.Acao

class RecyclerViewAdapter(private val acoes: List<Acao>, private var itemClickListener: OnItemClickListener? = null) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(acao: Acao)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_model, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nomeTextView: TextView = itemView.findViewById(R.id.text_nome)
        val tipoTextView: TextView = itemView.findViewById(R.id.text_tipo)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val acao = acoes[position]
        holder.nomeTextView.text = acao.nome
        holder.tipoTextView.text = acao.tipo

        holder.itemView.setOnClickListener {
            itemClickListener?.onItemClick(acao)

            val intent = Intent(holder.itemView.context, AlterActivity::class.java)
            intent.putExtra("id", acao.id)
            intent.putExtra("nome", acao.nome)
            intent.putExtra("tipo", acao.tipo)
            intent.putExtra("descricao", acao.descricao)
            holder.itemView.context.startActivity(intent)
        }
    }


    override fun getItemCount(): Int {
        return acoes.size
    }
}

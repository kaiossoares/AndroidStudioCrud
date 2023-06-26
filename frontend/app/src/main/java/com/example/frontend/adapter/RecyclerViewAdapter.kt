package com.example.frontend.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.frontend.R
import com.example.frontend.activity.AlterActivity
import com.example.frontend.api.Endpoint
import com.example.frontend.model.Acao
import com.example.frontend.network.RetrofitInitializer
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecyclerViewAdapter(private val acoes: MutableList<Acao>, private var itemClickListener: OnItemClickListener? = null) :
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
        val btnExcluir: ImageButton = itemView.findViewById(R.id.btn_excluir)
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

        holder.btnExcluir.setOnClickListener {
            showDeleteConfirmationDialog(holder.itemView.context, position)
        }
    }

    private fun showDeleteConfirmationDialog(context: Context, position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(context)
        alertDialogBuilder.setTitle("Excluir Ação")
        alertDialogBuilder.setMessage("Tem certeza que deseja excluir esta ação?")
        alertDialogBuilder.setPositiveButton("Sim") { dialog, _ ->
            deleteAcao(position)
            dialog.dismiss()
        }
        alertDialogBuilder.setNegativeButton("Não") { dialog, _ ->
            dialog.dismiss()
        }

        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }

    private fun deleteAcao(position: Int) {
        val acao = acoes[position]
        val endpoint = RetrofitInitializer.retrofit.create(Endpoint::class.java)

        endpoint.excluirAcao(acao.id).enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                if (response.isSuccessful) {
                    acoes.remove(acao)
                    notifyItemRemoved(position)
                    // Aqui você pode realizar ações adicionais, se necessário
                    // Por exemplo, você pode exibir uma mensagem de exclusão bem-sucedida
                } else {
                    // Tratar erro de exclusão da API
                    // Você pode exibir uma mensagem de erro ou tomar outras ações apropriadas
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                // Tratar falha na requisição
                // Você pode exibir uma mensagem de erro ou tomar outras ações apropriadas
            }
        })
    }


    override fun getItemCount(): Int {
        return acoes.size
    }
}
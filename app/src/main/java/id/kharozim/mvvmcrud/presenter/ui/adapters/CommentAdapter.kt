package id.kharozim.mvvmcrud.presenter.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kharozim.mvvmcrud.databinding.ItemListCommentBinding
import id.kharozim.mvvmcrud.domain.CommentDomain
import id.kharozim.mvvmcrud.domain.CommentModel

class CommentAdapter(private val context: Context, private val listener: CommentListener) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var list = mutableListOf<CommentDomain>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun editComment(domain: CommentDomain) {
        val index = list.indexOfFirst { it.id == domain.id }
        if (index != -1) {
            list[index] = domain
            notifyItemChanged(index)
        }
    }

    fun deleteComment(id: Int) {
        val index = list.indexOfFirst { it.id == id }
        if (index != -1)
            list.removeAt(index)
        notifyItemRemoved(index)

    }

    inner class ViewHolder(val binding: ItemListCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(domain: CommentDomain) {
            binding.tvName.text = "Name : ${domain.name}"
            binding.tvEmail.text = domain.email
            binding.tvBody.text = "Comment : ${domain.body}"
        }
    }

    interface CommentListener {
        fun onClick(model: CommentDomain)
        fun onDelete(model: CommentDomain)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemListCommentBinding.inflate(
                LayoutInflater.from(context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val domain = list[position]
        holder.bindData(domain)
        holder.binding.run {
            root.setOnClickListener { listener.onClick(domain) }
            btDel.setOnClickListener { listener.onDelete(domain) }
        }
    }

    override fun getItemCount(): Int = list.size
}


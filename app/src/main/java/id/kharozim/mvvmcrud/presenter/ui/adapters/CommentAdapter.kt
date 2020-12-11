package id.kharozim.mvvmcrud.presenter.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kharozim.mvvmcrud.databinding.ItemListCommentBinding
import id.kharozim.mvvmcrud.domain.CommentModel

class CommentAdapter(private val context: Context, private val listener: CommentListener) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var list = mutableListOf<CommentModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    fun editComment(model: CommentModel) {
        val index = list.indexOfFirst { it.id == model.id }
        if (index != -1) {
            list[index] = model
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
        fun bindData(commentModel: CommentModel) {
            binding.tvName.text = "Name : ${commentModel.name}"
            binding.tvEmail.text = commentModel.email
            binding.tvBody.text = "Comment : ${commentModel.body}"
        }
    }

    interface CommentListener {
        fun onClick(model: CommentModel)
        fun onDelete(model: CommentModel)

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
        val model = list[position]
        holder.bindData(model)
        holder.binding.run {
            root.setOnClickListener { listener.onClick(model) }
            btDel.setOnClickListener { listener.onDelete(model) }
        }
    }

    override fun getItemCount(): Int = list.size
}


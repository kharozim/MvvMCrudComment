package id.kharozim.mvvmcrud.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kharozim.mvvmcrud.databinding.ItemListCommentBinding
import id.kharozim.mvvmcrud.models.CommentModel

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

    inner class ViewHolder(val binding: ItemListCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(commentModel: CommentModel) {
            binding.tvName.text = "Name : ${commentModel.name}"
            binding.tvEmail.text = "Email : ${commentModel.email}"
            binding.tvBody.text = "Comment : ${commentModel.body}"
        }
    }

    interface CommentListener {
        fun onEdit(model: CommentModel)

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
            root.setOnClickListener { listener.onEdit(model) }
        }
    }

    override fun getItemCount(): Int = list.size
}


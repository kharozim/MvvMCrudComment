package id.kharozim.mvvmcrud.views.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.kharozim.mvvmcrud.databinding.ItemListCommentBinding
import id.kharozim.mvvmcrud.models.CommentModel

class CommentAdapter(private val context: Context) :
    RecyclerView.Adapter<CommentAdapter.ViewHolder>() {

    var list = mutableListOf<CommentModel>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    inner class ViewHolder(private val binding: ItemListCommentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(commentModel: CommentModel) {
            binding.tvName.text = "Name : ${commentModel.name}"
            binding.tvEmail.text = "Email : ${commentModel.email}"
            binding.tvBody.text = "Comment : ${commentModel.body}"
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemListCommentBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(list[position])
    }

    override fun getItemCount(): Int = list.size
}
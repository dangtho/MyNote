package com.dangtho.mynote.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.dangtho.mynote.R
import com.dangtho.mynote.data.model.N_PersonInfoResponse
import com.dangtho.mynote.databinding.ItemUsersBinding
import com.dangtho.mynote.view.base.BaseAdapter
import com.dangtho.mynote.view.base.BaseViewHodler

class UsersAdapter : BaseAdapter<N_PersonInfoResponse>() {
    fun setListUser(list: List<N_PersonInfoResponse>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHodler<N_PersonInfoResponse> {
        return UserViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHodler<N_PersonInfoResponse>, position: Int) {
        list ?: return
        holder.binData(list!![position])
    }

    override fun getItemCount(): Int {
        return list?.size ?: 0
    }
}

class UserViewHolder(private val binding: ItemUsersBinding) :
    BaseViewHodler<N_PersonInfoResponse>(binding.root) {
    override fun binData(item: N_PersonInfoResponse) {
        binding.tvFirstName.text = item.firstName
        binding.tvLastName.text = item.lastName
        Glide.with(binding.root.context).load(item.avatar)
            .placeholder(R.drawable.icon_plus)
            .error(R.drawable.icon_dot_menu)
            .centerCrop().into(binding.imgAvatar)
    }

}
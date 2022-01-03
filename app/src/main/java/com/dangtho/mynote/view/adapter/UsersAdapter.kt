package com.dangtho.mynote.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.dangtho.mynote.R
import com.dangtho.mynote.data.model.UserEntity
import com.dangtho.mynote.databinding.ItemUsersBinding
import com.dangtho.mynote.view.base.BaseAdapter
import com.dangtho.mynote.view.base.BaseViewHodler
import com.dangtho.mynote.view.viewmodel.UserFragmentViewModel

class UsersAdapter(
    private val viewModle: UserFragmentViewModel,
    private val lifeCycle: LifecycleOwner
) : BaseAdapter<UserEntity>() {
    fun setListUser(list: List<UserEntity>?) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHodler<UserEntity> {
        return UserViewHolder(
            ItemUsersBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BaseViewHodler<UserEntity>, position: Int) {
        Log.e("Vvvvvvvv", "lis $position" + viewModle.getItem(position).value.firstName)
//        lifeCycle.lifecycleScope.
//        viewModle.getItem(position)
        holder.binData(viewModle.getItem(position).value)
    }

    override fun getItemCount(): Int {
        return viewModle.listUser.value.size
    }
}

class UserViewHolder(private val binding: ItemUsersBinding) :
    BaseViewHodler<UserEntity>(binding.root) {
    override fun binData(item: UserEntity) {
        binding.tvFirstName.text = item.firstName
        binding.tvLastName.text = item.lastName
        Glide.with(binding.root.context).load(item.avatar)
            .placeholder(R.drawable.icon_plus)
            .error(R.drawable.icon_dot_menu)
            .centerCrop().into(binding.imgAvatar)
    }

}
package com.android.test.ui.home

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.test.R
import com.android.test.databinding.ActivityMainBinding
import com.android.test.utils.hideKeyboard
import com.android.test.utils.isNetworkAvailable
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    private val userViewModel: UserViewModel by viewModel()

    private val userAdapter by lazy {
        UserAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainBinding.rcvUserList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = userAdapter
        }
        mainBinding.btnSubmit.setOnClickListener {
            if (isNetworkAvailable()) {
                initObservers(
                    mainBinding.edtSearchUser.text.toString().trim()
                )
                mainBinding.ccProgress.visibility = View.VISIBLE
                hideKeyboard(this)
                mainBinding.edtSearchUser.clearFocus()
            }
        }
        initAdapter()
    }

    private fun initObservers(lbl: String) {
        lifecycleScope.launch {
            userViewModel.getUser(lbl).collectLatest {
                userAdapter.submitData(PagingData.empty())
                userAdapter.notifyDataSetChanged()
                userAdapter.submitData(it)
            }
        }
    }

    private fun initAdapter() {
        userAdapter.addLoadStateListener { loadState ->
            // show empty list
            loadState.refresh is LoadState.NotLoading && userAdapter.itemCount == 0
//            binding.tvNoResults.isVisible = isListEmpty

            // Only show the list if refresh succeeds.
            mainBinding.rcvUserList.isVisible = loadState.source.refresh is LoadState.NotLoading
            mainBinding.ccProgress.visibility = View.GONE

            // Show loading spinner during initial load or refresh.
//            handleLoading(loadState.source.refresh is LoadState.Loading)

            // Show the retry state if initial load or refresh fails.
//            binding.btnRetry.isVisible = loadState.source.refresh is LoadState.Error

            /**
             * loadState.refresh - represents the load state for loading the PagingData for the first time.
             * loadState.prepend - represents the load state for loading data at the start of the list.
             * loadState.append - represents the load state for loading data at the end of the list.
             * */
            // If we have an error, show a toast
            val errorState = when {
                loadState.append is LoadState.Error -> loadState.append as LoadState.Error
                loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
                loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
                else -> null
            }
            errorState?.let {
                Toast.makeText(this, it.error.message.toString(), Toast.LENGTH_SHORT).show()
            }
        }
    }

//    private fun handleLoading(loading: Boolean) {
//        /*with(binding) {
//            refreshLayout.isRefreshing = loading == true
//        }*/
//    }
}

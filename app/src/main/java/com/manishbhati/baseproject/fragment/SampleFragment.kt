package com.manishbhati.baseproject.fragment

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.manishbhati.baseproject.R
import com.manishbhati.baseproject.databinding.FragmentSampleBinding
import com.manishbhati.baseproject.helper.Helper
import com.manishbhati.baseproject.helper.SnapHelperOneByOne
import com.manishbhati.baseproject.util.Status
import com.manishbhati.baseproject.viewmodel.SampleViewModel
import com.manishbhati.domain.Response
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SampleFragment : BaseFragment() {
    lateinit var binding: FragmentSampleBinding


    private val viewModel: SampleViewModel by viewModels()

    @Inject
    lateinit var helper: Helper
    override fun getLayout(): Int {
        return R.layout.fragment_sample
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentSampleBinding.bind(view)

        viewModel.getData().observe(viewLifecycleOwner, {
            when (it.responseType) {
                Status.ERROR -> it.error?.message?.let { helper.showError(it) }
                Status.LOADING -> helper.showProgress()
                Status.SUCCESSFUL -> it.data?.let { data ->
                    helper.hideProgress()
                    binding.rvList.apply {
                        layoutManager =
                            LinearLayoutManager(
                                requireContext(),
                                LinearLayoutManager.HORIZONTAL,
                                true
                            )
                        adapter = ItemUserAdapter(data, rejectListener, acceptListener)
                        val helper = SnapHelperOneByOne()
                        helper.attachToRecyclerView(this)
                        post { scrollToPosition(0) }
                    }
                }
            }
        })
    }

    val acceptListener = { d: Response ->
        viewModel.updateData(d.id, true)
        showToast("accept ${d.name}")

        Unit
    }
    val rejectListener = { d: Response ->
        viewModel.updateData(d.id, false)
        showToast("reject ${d.name}")
        Unit
    }
}
package com.mbo.loblaws.feed

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mbo.loblaws.R
import com.mbo.loblaws.databinding.FeedFragmentBinding
import com.mbo.loblaws.injector
import com.mbo.loblaws.utils.launchWhenResumedIn
import com.mbo.loblaws.utils.onEach
import javax.inject.Inject

class FeedFragment : Fragment() {

    @Inject lateinit var store: FeedStore

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FeedFragmentBinding.inflate(inflater, container, false)

        binding.feedView.articleClickedEvent
            .onEach { store.dispatchAction(FeedAction.Navigation.ArticleDetail(it.article)) }
            .launchWhenResumedIn(viewLifecycleOwner.lifecycleScope)

        store.observeNavigation()
            .onEach(findNavController()::navigate)
            .launchWhenResumedIn(viewLifecycleOwner.lifecycleScope)

        store.observeState()
            .onEach(binding.feedView::render)
            .launchWhenResumedIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        store.dispatchAction(FeedAction.UpdateFeed)
    }
}
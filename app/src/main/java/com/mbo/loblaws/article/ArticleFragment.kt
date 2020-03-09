package com.mbo.loblaws.article

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.mbo.loblaws.databinding.ArticleFragmentBinding
import com.mbo.loblaws.injector
import com.mbo.loblaws.utils.launchWhenResumedIn
import com.mbo.loblaws.utils.onEach
import javax.inject.Inject

class ArticleFragment : Fragment() {

    @Inject lateinit var store: ArticleStore

    override fun onAttach(context: Context) {
        super.onAttach(context)
        context.injector.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = ArticleFragmentBinding.inflate(inflater, container, false)

        store.observeState()
            .onEach(binding.articleView::render)
            .launchWhenResumedIn(viewLifecycleOwner.lifecycleScope)

        return binding.root
    }
}
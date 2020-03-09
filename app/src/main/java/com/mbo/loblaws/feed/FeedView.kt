package com.mbo.loblaws.feed

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import androidx.core.text.HtmlCompat.FROM_HTML_MODE_COMPACT
import androidx.recyclerview.widget.*
import coil.api.load
import com.mbo.loblaws.databinding.FeedArticleItemBinding
import com.mbo.loblaws.databinding.FeedViewBinding
import com.mbo.loblaws.domain.Article
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.channelFlow

class FeedView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = FeedViewBinding.inflate(LayoutInflater.from(context), this)
    private val feedAdapter = FeedAdapter()
    private val articleClickedChannel = Channel<ArticleClicked>()
    val articleClickedEvent = channelFlow {
        for (event in articleClickedChannel) {
            send(event)
        }
    }

    init {
        binding.feedRecyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = feedAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    fun render(state: FeedState) {
        feedAdapter.submitList(state.articles)
    }


    inner class FeedAdapter :
        ListAdapter<Article, FeedAdapter.ArticleViewHolder>(FeedDiffUtilCallback()) {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
            val binding = FeedArticleItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
            return ArticleViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
            val article = getItem(position)
            holder.apply {
                bind(article)
                itemView.setOnClickListener {
                    articleClickedChannel.offer(ArticleClicked(article))
                }
            }
        }

        inner class ArticleViewHolder(
            private val binding: FeedArticleItemBinding
        ) : RecyclerView.ViewHolder(binding.root) {

            fun bind(article: Article) {
                binding.apply {
                    articleThumbnail.visibility =
                        if (article.thumbnailUrl == null) GONE else VISIBLE
                    article.thumbnailUrl?.let { articleThumbnail.load(it) }
                    articleTitle.text = article.title
                }
            }
        }
    }

    data class ArticleClicked(val article: Article)

    class FeedDiffUtilCallback : DiffUtil.ItemCallback<Article>() {
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }

    }
}
package com.mbo.loblaws.article

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.text.HtmlCompat
import coil.api.load
import com.mbo.loblaws.databinding.ArticleViewBinding

class ArticleView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding = ArticleViewBinding.inflate(LayoutInflater.from(context), this)

    fun render(state: ArticleState) {
        val article = state.article
        binding.apply {
            articleImage.visibility =
                if (article.thumbnailUrl == null) GONE else VISIBLE
            article.thumbnailUrl?.let { articleImage.load(it) }
            articleBody.text = article.body?.let { text ->
                HtmlCompat.fromHtml(text, HtmlCompat.FROM_HTML_MODE_LEGACY)
            }
        }
    }
}
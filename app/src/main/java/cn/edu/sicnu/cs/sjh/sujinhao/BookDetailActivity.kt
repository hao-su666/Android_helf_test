package cn.edu.sicnu.cs.sjh.sujinhao

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import cn.edu.sicnu.cs.sjh.sujinhao.databinding.ActivityBookDetailBinding

/**
 * 展示单本书籍的完整信息。
 */
class BookDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyWindowInsets()

        val book = Book(
            name = intent.getStringExtra(EXTRA_NAME).orEmpty(),
            author = intent.getStringExtra(EXTRA_AUTHOR).orEmpty(),
            publisher = intent.getStringExtra(EXTRA_PUBLISHER).orEmpty(),
            price = intent.getDoubleExtra(EXTRA_PRICE, 0.0)
        )

        binding.textBookName.text = getString(R.string.book_name_format, book.name)
        binding.textBookAuthor.text = book.author
        binding.textBookPublisher.text = book.publisher
        binding.textBookPrice.text = getString(R.string.detail_price_format, book.price)
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    companion object {
        private const val EXTRA_NAME = "extra_name"
        private const val EXTRA_AUTHOR = "extra_author"
        private const val EXTRA_PUBLISHER = "extra_publisher"
        private const val EXTRA_PRICE = "extra_price"

        fun newIntent(context: Context, book: Book): Intent =
            Intent(context, BookDetailActivity::class.java).apply {
                putExtra(EXTRA_NAME, book.name)
                putExtra(EXTRA_AUTHOR, book.author)
                putExtra(EXTRA_PUBLISHER, book.publisher)
                putExtra(EXTRA_PRICE, book.price)
            }
    }
}

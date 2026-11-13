package cn.edu.sicnu.cs.sjh.sujinhao

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cn.edu.sicnu.cs.sjh.sujinhao.databinding.ItemBookBinding

/**
 * 书籍列表适配器，负责绑定书籍数据并处理列表项的点击事件。
 */
class BookAdapter(
    private val books: List<Book>,
    private val onBookClick: (Book) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(books[position])
    }

    override fun getItemCount(): Int = books.size

    inner class BookViewHolder(
        private val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            val context = binding.root.context
            binding.textBookName.text = context.getString(R.string.book_name_format, book.name)
            binding.textBookAuthor.text = context.getString(R.string.label_author, book.author)
            binding.textBookPublisher.text = context.getString(R.string.label_publisher, book.publisher)
            binding.textBookPrice.text = context.getString(R.string.price_format, book.price)
            binding.root.setOnClickListener { onBookClick(book) }
        }
    }
}

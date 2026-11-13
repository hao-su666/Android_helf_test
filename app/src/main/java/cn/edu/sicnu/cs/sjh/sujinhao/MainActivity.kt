package cn.edu.sicnu.cs.sjh.sujinhao

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import androidx.recyclerview.widget.LinearLayoutManager
import cn.edu.sicnu.cs.sjh.sujinhao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    /** 在代码中创建的书籍数组，初始顺序与价格无关。 */
    private val books = arrayOf(
        Book("第一行代码（第3版）", "郭霖", "人民邮电出版社", 69.00),
        Book("Kotlin 编程权威指南", "Bill Phillips", "中国电力出版社", 129.00),
        Book("Java 核心技术 卷 I", "Cay S. Horstmann", "机械工业出版社", 149.00),
        Book("深入理解计算机系统", "Randal E. Bryant", "机械工业出版社", 139.00),
        Book("算法导论", "Thomas H. Cormen", "机械工业出版社", 128.00),
        Book("平凡的世界", "路遥", "北京十月文艺出版社", 108.00)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applyWindowInsets()

        books.sortBy { it.price }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = BookAdapter(books.toList()) { book -> openBookDetail(book) }
            setHasFixedSize(true)
        }
    }

    private fun applyWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { view, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.updatePadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun openBookDetail(book: Book) {
        startActivity(BookDetailActivity.newIntent(this, book))
    }
}

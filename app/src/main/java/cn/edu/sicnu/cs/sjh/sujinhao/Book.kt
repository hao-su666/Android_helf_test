package cn.edu.sicnu.cs.sjh.sujinhao

/**
 * 书籍实体类，保存书名、作者、出版社和价格。
 */
data class Book(
    val name: String,
    val author: String,
    val publisher: String,
    val price: Double
)

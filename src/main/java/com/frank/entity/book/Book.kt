package com.frank.entity.book

import java.math.BigDecimal

class Book {

    var title: String? = null
    var author: String? = null
    var isbn: String? = null
    var genre: BookGenre? = null
    var actualPrice: BigDecimal? = null
    var shelf: BookShelf? = null

    val id: Any
        get() = isbn!!
}

package com.frank.entity.book

import java.util.ArrayList

class BookShelf(private val bookPlaceCount: Int, private val genre: BookGenre) {

    private val bookInShelf: MutableList<Book>

    init {
        this.bookInShelf = ArrayList(bookPlaceCount)
    }

    fun place(bookCollection: BookCollection) {
        if (bookPlaceCount - bookInShelf.size < 0) {
            throw RuntimeException("No space in shelf " + this + " to store the collection " + bookCollection)
        }
        bookCollection.asList().forEach { book ->
            bookInShelf.add(book)
            book.genre = this.genre
            book.shelf = this
        }
    }

    fun remove(bookCollection: BookCollection) {
        bookCollection.asList().forEach { book ->
            this.bookInShelf.remove(book.id)
            book.shelf = null
        }
    }

    operator fun contains(book: Book): Boolean {
        return bookInShelf.contains(book)
    }

    operator fun contains(bookCollection: BookCollection): Boolean {
        return bookCollection.asList().stream().allMatch { book -> bookInShelf.contains(book) }
    }

}

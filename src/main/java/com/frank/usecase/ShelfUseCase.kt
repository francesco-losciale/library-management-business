package com.frank.usecase

import com.frank.entity.book.BookCollection
import com.frank.entity.book.Book
import com.frank.entity.book.BookGenre
import com.frank.entity.book.BookShelf

class ShelfUseCase {

    fun newShelf(bookGenre: BookGenre?, placesCount: Int): BookShelf {
        return BookShelf(placesCount, bookGenre!!)
    }

    fun newShelf(placesCount: Int): BookShelf {
        return newShelf(null, placesCount)
    }

    fun addBookToShelf(book: Book, bookShelf: BookShelf) {
        val bookCollection = BookCollection()
        bookCollection.add(book)
        bookShelf.place(bookCollection)
    }

    fun moveCollectionFromShelfToShelf(bookCollection: BookCollection, src: BookShelf, dest: BookShelf) {
        src.remove(bookCollection)
        dest.place(bookCollection)
    }
}

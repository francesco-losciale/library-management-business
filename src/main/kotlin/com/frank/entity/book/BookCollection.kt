package com.frank.entity.book

import java.util.ArrayList
import java.util.Arrays
import java.util.stream.Stream

class BookCollection {

    private val collection = ArrayList<Book>()

    constructor() {}

    constructor(vararg collection: Book) {
        Arrays.stream(collection).forEach { book -> this.collection.add(book) }
    }

    fun add(book: Book) {
        collection.add(book)
    }

    operator fun contains(book: Book): Boolean {
        return collection.contains(book)
    }

    fun seek(book: Book): Book {
        for (currentBook in collection) {
            if (currentBook == book) {
                return book
            }
        }
        throw RuntimeException("Book not found")
    }

    fun move(book: Book, bookCollection: BookCollection) {
        val remove = this.collection.remove(book)
        if (remove) {
            bookCollection.add(book)
        } else {
            throw RuntimeException("No occurrence of $book was found")
        }
    }

    fun asList(): List<Book> {
        return ArrayList(collection)
    }

    companion object {

        // TODO 1: if you don't put this annotation, from java context this method will not be found as static.
        @JvmStatic fun seek(book: Book, vararg collections: BookCollection): Book {
            for (bookCollection in collections) {
                if (bookCollection.seek(book) != null) {
                    return bookCollection.seek(book)
                }
            }
            throw RuntimeException("Book not found")
        }
    }
}

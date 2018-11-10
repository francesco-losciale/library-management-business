import com.frank.entity.book.BookGenre
import com.frank.entity.book.BookShelf
import java.math.BigDecimal

data class Book(val title: String,
                val author: String,
                val isbn: String,
                val genre: BookGenre,
                val actualPrice: BigDecimal, // TODO do you have to use BigDecimal?
                val shelf: BookShelf)

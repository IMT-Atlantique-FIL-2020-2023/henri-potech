package fr.henri.potech.bookshop.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.domain.Library


class BookListViewModel : ViewModel() {
    val isLoading = MutableLiveData<Boolean>()
    val books: LiveData<Result<List<Book>>> = liveData {
        isLoading.value = true
        try {
            emit(Result.success(Library.fetchBooks().books))
        } catch (ioException: Exception) {
            emit(Result.failure(ioException))
        }
        isLoading.value = false
    }
}

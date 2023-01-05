package fr.henri.potech.bookshop.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import fr.henri.potech.bookshop.data.books.BooksRepository
import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.domain.Library
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * UI state for the Home route.
 *
 * This is derived from [HomeViewModelState], but split into two possible subclasses to more
 * precisely represent the state available to render the UI.
 */
sealed interface HomeUiState {

    val isLoading: Boolean
//    val errorMessages: List<ErrorMessage>
    val searchInput: String

    /**
     * There are no books to render.
     *
     * This could either be because they are still loading or they failed to load, and we are
     * waiting to reload them.
     */
    data class NoBook(
        override val isLoading: Boolean,
//        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState

    /**
     * There are books to render, as contained in [library].
     */
    data class HasBooks(
        val library: Library,
        val selectedBook: Book?,
        val isArticleOpen: Boolean,
        override val isLoading: Boolean,
//        override val errorMessages: List<ErrorMessage>,
        override val searchInput: String
    ) : HomeUiState
}

/**
 * An internal representation of the Home route state, in a raw form
 */
private data class HomeViewModelState(
    val library: Library? = null,
    val selectedBookISBN: String? = null, // TODO back selectedBookISBN in a SavedStateHandle
    val isArticleOpen: Boolean = false,
    val favorites: Set<String> = emptySet(),
    val isLoading: Boolean = false,
//    val errorMessages: List<ErrorMessage> = emptyList(),
    val searchInput: String = "",
) {

    /**
     * Converts this [HomeViewModelState] into a more strongly typed [HomeUiState] for driving
     * the ui.
     */
    fun toUiState(): HomeUiState =
        if (library == null) {
            HomeUiState.NoBook(
                isLoading = isLoading,
//                errorMessages = errorMessages,
                searchInput = searchInput
            )
        } else {
            HomeUiState.HasBooks(
                library = library,
                // Determine the selected post. This will be the post the user last selected.
                // If there is none (or that post isn't in the current feed), default to the
                // highlighted post
                selectedBook = library.books.find {
                    it.isbn == selectedBookISBN
                },
                isArticleOpen = isArticleOpen,
                isLoading = isLoading,
//                errorMessages = errorMessages,
                searchInput = searchInput
            )
        }
}

/**
 * ViewModel that handles the business logic of the Home screen
 */
class HomeViewModel(
    private val booksRepository: BooksRepository
) : ViewModel() {

    private val viewModelState = MutableStateFlow(HomeViewModelState(isLoading = true))

    // UI state exposed to the UI
    val uiState = viewModelState
        .map(HomeViewModelState::toUiState)
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    init {
        refreshBooks()
    }

    /**
     * Refresh books and update the UI state accordingly
     */
    private fun refreshBooks() {
        // Ui state is refreshing
        viewModelState.update { it.copy(isLoading = true) }

        viewModelScope.launch {
            val result: Result<Library> = booksRepository.getLibrary()
            viewModelState.update { viewModelState ->
                result.onFailure { println("Error when loading books") }
//                val errorMessages = it.errorMessages + ErrorMessage(
//                    id = UUID.randomUUID().mostSignificantBits,
//                    messageId = R.string.load_error
//                )
//                it.copy(errorMessages = errorMessages, isLoading = false)

                result
                    .map { viewModelState.copy(library = it, isLoading = false) }
                    .getOrDefault(viewModelState)
            }
        }
    }

    /**
     * Selects the given article to view more information about it.
     */
    fun selectArticle(bookISBN: String) {
        // Treat selecting a detail as simply interacting with it
        interactedWithArticleDetails(bookISBN)
    }

//    /**
//     * Notify that an error was displayed on the screen
//     */
//    fun errorShown(errorId: Long) {
//        viewModelState.update { currentUiState ->
//            val errorMessages = currentUiState.errorMessages.filterNot { it.id == errorId }
//            currentUiState.copy(errorMessages = errorMessages)
//        }
//    }

    /**
     * Notify that the user interacted with the feed
     */
    fun interactedWithFeed() {
        viewModelState.update {
            it.copy(isArticleOpen = false)
        }
    }

    /**
     * Notify that the user interacted with the article details
     */
    private fun interactedWithArticleDetails(bookISBN: String) {
        viewModelState.update {
            it.copy(
                selectedBookISBN = bookISBN,
                isArticleOpen = true
            )
        }
    }

    /**
     * Notify that the user updated the search query
     */
    fun onSearchInputChanged(searchInput: String) {
        viewModelState.update {
            it.copy(searchInput = searchInput)
        }
    }

    /**
     * Factory for HomeViewModel that takes BooksRepository as a dependency
     */
    companion object {
        fun provideFactory(
            booksRepository: BooksRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return HomeViewModel(booksRepository) as T
            }
        }
    }
}
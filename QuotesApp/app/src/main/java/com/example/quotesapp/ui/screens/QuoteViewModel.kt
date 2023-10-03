package com.example.quotesapp.ui.screens

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.quotesapp.QuotesApplication
import com.example.quotesapp.data.QuoteRepository
import com.example.quotesapp.model.QuoteModel
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException


sealed interface QuoteUiState{


    data class Success(
        val quoteData : List<QuoteModel>
    ) : QuoteUiState

    object Error: QuoteUiState

    object  Loading: QuoteUiState

}


class QuoteViewModel(private val quoteRepository: QuoteRepository) : ViewModel() {

    var quoteUiState: QuoteUiState by mutableStateOf(QuoteUiState.Loading)
        private set

    init {
        getQuotes()
    }

    fun getQuotes(){
        viewModelScope.launch {
            quoteUiState = QuoteUiState.Loading
            quoteUiState = try{
                QuoteUiState.Success(
                    quoteRepository.getQuotes()
                )
            }catch (e: IOException){
                Log.e("QuoteViewModel", "IOException: ${e.message}")
                QuoteUiState.Error
            }catch (e: HttpException){
                Log.e("QuoteViewModel", "HttpException: ${e.message}")
                QuoteUiState.Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as QuotesApplication)
                val quoteRepository = application.container.quoteRepository
                QuoteViewModel(quoteRepository = quoteRepository)
            }
        }
    }

    //Ui State
    //ye state viewmodel class se hi view or edit ho skti hai
    //We can't change it from an external class
//    private val _uiState = MutableStateFlow(QuoteUiState())
//
//    //Backing property to avoid state updated from other classes
//    val uiState: StateFlow<QuoteUiState> = _uiState.asStateFlow()
//
//    private lateinit var  currentQuote: String
//    private lateinit var currentQuoteAuthor: String
//
//    private var previousQuote: MutableSet<QuoteUiState> = mutableSetOf()
//
//    private lateinit var currentData: QuoteUiState
//
//    private var count: Int = 0
//
//    private fun pickRandomQuote(): QuoteUiState {
//        if(count<15){
//            currentData = QuotesData().loadQuotes().get(count)
//            count++
//        }else{
//            count=0
//            currentData = QuotesData().loadQuotes().get(count)
//        }
//
//        if(previousQuote.contains(currentData)){
//            return pickRandomQuote()
//        }else{
//            previousQuote.clear()
//            previousQuote.add(currentData)
//            return currentData
//        }
//    }
//
//    init {
//        _uiState.value = QuoteUiState(pickRandomQuote().quote,pickRandomQuote().author)
//    }
//
//    fun updateQuoteState(){
//        _uiState.update { currentState ->
//            currentState.copy(
//              quote = pickRandomQuote().quote,
//                author = pickRandomQuote().author
//            )
//        }
//    }

}
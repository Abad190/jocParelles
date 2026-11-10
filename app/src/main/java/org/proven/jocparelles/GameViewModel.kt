package org.proven.jocparelles

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel() {
    var model by mutableStateOf(ModelGame().initGame())

    /* Metodes d'esdeveniments onClick */
    fun cardClick(index: Int) {
        Log.d("CARDS", "Click a la carta $index")
        // Si ja hi ha dues cartes girades, ignorem clics fins que es validi
        if (model.selected.size >= 2) return

        model = model.flipCard(index)
        // Ja no hi ha delay ni checkPair aquí: es comprova a validateClick()
    }

    fun restartClick() {
        model = ModelGame().initGame()
    }

    fun validateClick() {
        // Només comprova si hi ha exactament dues cartes girades
        if (model.selected.size == 2) {
            model = model.checkPair()
        }
    }
}
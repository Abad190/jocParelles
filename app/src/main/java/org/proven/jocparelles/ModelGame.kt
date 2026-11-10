package org.proven.jocparelles

class ModelGame(
    val cards: List<Int> = listOf(
        R.drawable.eugenio,
        R.drawable.eugenio,
        R.drawable.chiquito,
        R.drawable.chiquito,
        R.drawable.rubianes,
        R.drawable.rubianes
    ),
    val cardsView: List<Int> = listOf(
        R.drawable.back,
        R.drawable.back,
        R.drawable.back,
        R.drawable.back,
        R.drawable.back,
        R.drawable.back
    ),
    // Índexs de les cartes girades que encara no s'han resolt (0, 1 o 2)
    val selected: List<Int> = emptyList(),
    // Índexs de les cartes que ja formen parella
    val matched: Set<Int> = emptySet()
) {
    /* Barreja les cartes i les posa totes de cara avall */
    fun initGame(): ModelGame {
        val newCardsView = this.cardsView.map { R.drawable.back }
        return ModelGame(
            cards = this.cards.shuffled(),
            cardsView = newCardsView
        )
    }

    /* Gira una carta. Si no es pot girar, retorna el mateix model */
    fun flipCard(index: Int): ModelGame {
        if (index in matched || index in selected || selected.size >= 2) return this
        val newCardsView = cardsView.toMutableList()
        newCardsView[index] = cards[index]
        return ModelGame(cards, newCardsView, selected + index, matched)
    }

    /* Quan hi ha dues cartes girades: les deixa descobertes si són parella, o les tapa si no */
    fun checkPair(): ModelGame {
        if (selected.size < 2) return this
        val (a, b) = selected
        return if (cards[a] == cards[b]) {
            ModelGame(cards, cardsView, emptyList(), matched + setOf(a, b))
        } else {
            val newCardsView = cardsView.toMutableList()
            newCardsView[a] = R.drawable.back
            newCardsView[b] = R.drawable.back
            ModelGame(cards, newCardsView, emptyList(), matched)
        }
    }

    fun isGameOver(): Boolean = matched.size == cards.size
}

package org.proven.jocparelles

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun GameScreen(gameViewModel: GameViewModel, modifier: Modifier = Modifier) {
    val model = gameViewModel.model
    val shape = RoundedCornerShape(10.dp)

    /*Compose*/
    Box(modifier = Modifier.fillMaxSize()) {
        // Fons
        Image(
            painter = painterResource(id = R.drawable.fondo1),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Joc de les Parelles",
                color = Color(0xFFFFA500),
                fontSize = 28.sp,
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(32.dp))

            // Cartes: 2 files de 3
            model.cardsView.chunked(3).forEachIndexed { rowIndex, rowCards ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    rowCards.forEachIndexed { col, card ->
                        val index = rowIndex * 3 + col
                        Image(
                            painter = painterResource(id = card),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .weight(1f)
                                .aspectRatio(0.68f)
                                .clip(shape)
                                .border(3.dp, Color.White, shape)
                                .clickable { gameViewModel.cardClick(index) }
                        )
                    }
                }
                Spacer(modifier = Modifier.height(40.dp))
            }

            Button(onClick = { gameViewModel.validateClick() }) {
                Text("VALIDATE")
            }

            if (model.isGameOver()) {
                Text(
                    text = "Has guanyat!",
                    color = Color.White,
                    fontSize = 24.sp,
                    modifier = Modifier.padding(top = 24.dp)
                )
                Button(
                    onClick = { gameViewModel.restartClick() },
                    modifier = Modifier.padding(top = 8.dp)
                ) {
                    Text("Tornar a jugar")
                }
            }
        }
    }
}
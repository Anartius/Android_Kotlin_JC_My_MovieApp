package com.example.myjcmovieapp.wigets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.myjcmovieapp.model.Movie
import com.example.myjcmovieapp.model.getMovies

@Preview
@Composable
fun MovieRow(
    movie: Movie = getMovies()[0],
    onItemClick: (String) -> Unit = {}
) {
    val expanded = remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .padding(4.dp)
        .fillMaxWidth()
        .clickable {
            onItemClick(movie.id)
        },
        shape = RoundedCornerShape(CornerSize(16.dp)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(modifier = Modifier
                .padding(12.dp)
                .size(100.dp),
                shape = CircleShape,
                tonalElevation = 4.dp,
            ) {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(movie.images[0])
                        .crossfade(true)
                        .transformations(CircleCropTransformation())
                        .build(),
                    contentDescription = "Movie poster"
                )
            }

            Column(modifier = Modifier.padding(4.dp)) {
                Text(
                    text = movie.title,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Genre: ${movie.genre}",
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    text = "Released: ${movie.year}",
                    color = Color.Black,
                    fontSize = 14.sp
                )

                AnimatedVisibility(visible = expanded.value) {
                    Column {
                        Text( buildAnnotatedString {
                            withStyle(style = SpanStyle(
                                Color.DarkGray,
                                fontSize = 13.sp
                            )) {
                                append("Plot: ")
                            }
                            withStyle(style = SpanStyle(
                                Color.DarkGray,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Light
                            )) {
                                append(movie.plot)
                            }
                        },
                            modifier = Modifier.padding(5.dp)
                        )

                        Divider(modifier = Modifier.padding(4.dp))

                        Text(
                            text = "Director: ${movie.director}",
                            color = Color.Black,
                            fontSize = 14.sp
                        )

                        Text(
                            text = "Actors: ${movie.actors}",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                        Text(
                            text = "Rating: ${movie.rating}",
                            color = Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }

                Icon(
                    imageVector = if (expanded.value) {
                        Icons.Default.KeyboardArrowUp
                    } else {
                        Icons.Default.KeyboardArrowDown
                    },
                    contentDescription = "Down Arrow",
                    tint = Color.DarkGray,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable {
                            expanded.value = !expanded.value
                        }
                )
            }

        }

    }
}

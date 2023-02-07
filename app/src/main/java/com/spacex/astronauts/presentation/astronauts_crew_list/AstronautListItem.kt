package com.spacex.astronauts.presentation.astronauts_crew_list

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.spacex.astronauts.domain.model.Astronaut

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun AstronautListItem(astronaut: Astronaut) {
    val uriHandler = LocalUriHandler.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp), shape = RoundedCornerShape(15.dp), elevation = 5.dp
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 10.dp)
        ) {
            GlideImage(
                model = astronaut.imageUrl,
                contentDescription = astronaut.name,
                modifier = Modifier.fillMaxWidth()
            )
            Column(modifier = Modifier.padding(5.dp)) {
                Text(
                    text = buildAnnotatedString {
                        append("Name: ")
                        pushStyle(
                            style = SpanStyle(
                                color = Color.Black,
                                fontWeight = FontWeight.Bold,
                                fontSize = 20.sp
                            )
                        )
                        append(astronaut.name)
                        pop()
                    },
                    textAlign = TextAlign.Left,
                )
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = "Agency: ${astronaut.agency}")
                Spacer(modifier = Modifier.width(2.dp))
                Text(text = buildAnnotatedString {
                    append("Status: ")
                    pushStyle(
                        style = SpanStyle(
                            color = Color.Green, fontWeight = FontWeight.SemiBold,
                            fontStyle = FontStyle.Italic
                        )
                    )
                    append(astronaut.currentStatus.uppercase())
                    pop()
                })

                // wiki
                val wiki = buildAnnotatedString {
                    append("Wiki: ")
                    pushStringAnnotation(tag = "wiki", annotation = astronaut.wikipediaUrl)
                    withStyle(
                        style = SpanStyle(
                            color = Color.Blue,
                            textDecoration = TextDecoration.Underline
                        )
                    ) {
                        append(astronaut.wikipediaUrl)
                    }
                    pop()
                }
                ClickableText(text = wiki, style = MaterialTheme.typography.body2) {
                    wiki.getStringAnnotations(tag = "wiki", start = it, end = it).firstOrNull()
                        ?.let {
                            if (it.tag == "wiki") {
                                uriHandler.openUri(it.item)
                            }
                        }
                }

            }

        }
    }
}
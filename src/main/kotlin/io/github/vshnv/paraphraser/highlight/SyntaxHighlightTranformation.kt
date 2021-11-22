package io.github.vshnv.paraphraser.highlight

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle


@OptIn(ExperimentalStdlibApi::class)
val sampleHighlightProperty = buildMap<String, Color> {
    val blue = Color(0xFF497CE3)
    val yellow = Color(0xFF82DFA3)
    listOf("import", "public", "private", "protected", "static", "extends", "implements").forEach { put(it, blue) }
    listOf("class", "void" , "int", "double", "float", "boolean", "byte", "short", "String").forEach { put(it, yellow) }
}

class SyntaxHighlightTranformation(private val highlightProperty: Map<String, Color>): VisualTransformation {


    fun buildAnnotatedStringWithColors(text:String): AnnotatedString {
        val words: List<String> = text.split("(?=[ ()\\[\\]{}])|(?<=[ ()\\[\\]{}])".toRegex())// splits by whitespace
        val builder = AnnotatedString.Builder()
        for (word in words) {
            builder.withStyle(style = SpanStyle(color = highlightProperty[word] ?: Color.White)) {
                append(word)
            }
        }
        return builder.toAnnotatedString()
    }

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            buildAnnotatedStringWithColors(text.toString()),
            OffsetMapping.Identity)
    }
}
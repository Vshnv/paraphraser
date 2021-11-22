package io.github.vshnv.paraphraser

import ApplicationTheme
import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import firaCode
import io.github.vshnv.paraphraser.compile.*
import io.github.vshnv.paraphraser.highlight.SyntaxHighlightTranformation
import io.github.vshnv.paraphraser.highlight.sampleHighlightProperty
import org.objectweb.asm.ClassReader
import org.objectweb.asm.util.TraceClassVisitor
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import javax.tools.JavaCompiler.CompilationTask
import javax.tools.ToolProvider

private val INITIAL_SOURCE = """
    public class BasicClass {
        public static void main(final String[] args) {
        
        }
    }
""".trimIndent()

@Composable
fun App(translator: Translator) {
    var text by remember { mutableStateOf(INITIAL_SOURCE) }
    val bytecode by remember(text) { mutableStateOf(translator.translate(text)) }
    ApplicationTheme {
        Row(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.weight(0.5f).background(MaterialTheme.colors.secondary)
                    .padding(10.dp)
            ) {
                CodeBlock(MaterialTheme.colors.secondaryVariant, MaterialTheme.colors.onSecondary, text) {
                    text = it.replace("\t", "  ")
                }
            }
            Column(modifier = Modifier.weight(0.5f).background(MaterialTheme.colors.secondaryVariant).padding(10.dp)) {
                CodeBlock(MaterialTheme.colors.secondary, MaterialTheme.colors.onSecondary, bytecode) {}
            }
        }
    }
}

@Composable
fun CodeBlock(background: Color, textColor: Color, text: String, setText: (String) -> Unit) {
    val scrollStateHorizontal = rememberScrollState(0)
    val scrollStateVertical = rememberScrollState(0)


    Row(modifier = Modifier.fillMaxHeight(0.99f).fillMaxWidth()) {
        BasicTextField(
            modifier = Modifier.fillMaxHeight().fillMaxWidth(0.99f).clip(RoundedCornerShape(10.dp))
                .background(background).padding(5.dp).padding(5.dp).horizontalScroll(scrollStateHorizontal).verticalScroll(scrollStateVertical),
            value = text,
            onValueChange = setText,
            textStyle = TextStyle(fontFamily = firaCode, color = textColor),
            visualTransformation = SyntaxHighlightTranformation(sampleHighlightProperty)
        )
        VerticalScrollbar(
            modifier = Modifier
                .width(5.dp).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(scrollStateVertical)
        )
    }
    HorizontalScrollbar(
        modifier = Modifier
            .fillMaxWidth().fillMaxHeight().fillMaxWidth(),
        adapter = rememberScrollbarAdapter(scrollStateHorizontal)
    )
}

fun main() {
    val javaToBytecodeTranslator = JavaToBytecodeTranslator("BasicClass")
    application {
        Window(onCloseRequest = ::exitApplication, title = "Paraphraser") {
            App(javaToBytecodeTranslator)
        }
    }
}

@Composable
@Preview
fun AppPreview() {
    App{"test"}
}
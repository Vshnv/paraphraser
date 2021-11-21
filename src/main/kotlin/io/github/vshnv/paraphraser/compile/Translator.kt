package io.github.vshnv.paraphraser.compile

import org.objectweb.asm.ClassReader
import org.objectweb.asm.util.TraceClassVisitor
import java.io.ByteArrayOutputStream
import java.io.PrintWriter
import javax.tools.JavaCompiler
import javax.tools.ToolProvider

fun interface Translator {
    fun translate(source: String): String
}

class JavaToBytecodeTranslator(private val publicClassName: String) : Translator {
    private val compiler = ToolProvider.getSystemJavaCompiler()
    private val classObject = InMemoryJavaFileObject(publicClassName)
    private val fileManager = InMemoryJavaFileManager(compiler.getStandardFileManager(null, null, null), classObject)

    override fun translate(source: String): String {
        if (source.trim().isEmpty()) return ""
        ByteArrayOutputStream().use { os ->
            ByteArrayOutputStream().use { er ->
                val task: JavaCompiler.CompilationTask =
                    compiler.getTask(PrintWriter(er), fileManager, null, null, null, listOf(SourceJavaFileObject(publicClassName, source)))
                val success = task.call()

                if (!success) return "Compilation Failed...\n" + String(er.toByteArray())

                val reader = ClassReader(fileManager.fileObject.getByteCode())
                fileManager.fileObject.reset()
                val tcv = TraceClassVisitor(PrintWriter(os))
                reader.accept(tcv, 0)

                val result = String(os.toByteArray())
                os.reset()

                return result
            }
        }
    }
}
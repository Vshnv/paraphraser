package io.github.vshnv.paraphraser.compile

import java.io.ByteArrayOutputStream
import java.io.OutputStream
import java.net.URI
import javax.tools.JavaFileObject
import javax.tools.SimpleJavaFileObject

class InMemoryJavaFileObject(val className: String): SimpleJavaFileObject(URI(className), JavaFileObject.Kind.CLASS) {
    private val outputStream = ByteArrayOutputStream()

    override fun openOutputStream(): OutputStream {
        return outputStream
    }

    fun getByteCode() = outputStream.toByteArray()

    fun reset() = outputStream.reset()
}
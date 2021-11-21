package io.github.vshnv.paraphraser.compile

import java.net.URI
import javax.tools.JavaFileObject
import javax.tools.SimpleJavaFileObject


class SourceJavaFileObject(name: String, val code: String) :
    SimpleJavaFileObject(
        URI.create("string:///" + name.replace('.', '/') + JavaFileObject.Kind.SOURCE.extension),
        JavaFileObject.Kind.SOURCE
    ) {
    override fun getCharContent(ignoreEncodingErrors: Boolean): CharSequence {
        return code
    }
}
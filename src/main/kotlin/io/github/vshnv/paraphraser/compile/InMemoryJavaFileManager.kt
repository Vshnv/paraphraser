package io.github.vshnv.paraphraser.compile

import javax.tools.FileObject
import javax.tools.ForwardingJavaFileManager
import javax.tools.JavaFileManager
import javax.tools.JavaFileObject

class InMemoryJavaFileManager(fileManager: JavaFileManager, val fileObject: InMemoryJavaFileObject): ForwardingJavaFileManager<JavaFileManager>(fileManager) {
    override fun getJavaFileForOutput(
        location: JavaFileManager.Location?,
        className: String?,
        kind: JavaFileObject.Kind?,
        sibling: FileObject?
    ): JavaFileObject {
        return fileObject
    }
}
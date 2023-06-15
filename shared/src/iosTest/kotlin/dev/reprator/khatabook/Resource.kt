package dev.reprator.khatabook

import kotlinx.cinterop.*
import platform.posix.*
import platform.Foundation.NSBundle

actual class Resource actual constructor(actual val name: String) {

  private val path = NSBundle.mainBundle.pathForResource("$RESOURCE_PATH/$name", ".json") ?: ""
  private val file: CPointer<FILE>? = fopen(path, "r")

  actual fun exists(): Boolean = file != null

  actual fun readText(): String {
    fseek(file, 0, SEEK_END)
    val size = ftell(file)
    rewind(file)

    return memScoped {
      val tmp = allocArray<ByteVar>(size)
      fread(tmp, sizeOf<ByteVar>().convert(), size.convert(), file)
      tmp.toKString()
    }
  }
}

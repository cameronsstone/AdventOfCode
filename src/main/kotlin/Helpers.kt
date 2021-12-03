import java.io.FileNotFoundException
import kotlin.reflect.KClass

fun getResourceAsText(path: String): String {
    return {}.javaClass.getResourceAsText(path)
}

fun <T : Any?> Class<T>.getResourceAsText(path: String): String {
    return this.getResource(path)?.readText() ?: throw FileNotFoundException(path)
}

fun <T : Any> KClass<T>.getResourceAsText(path: String): String {
    return this.java.getResourceAsText(path)
}

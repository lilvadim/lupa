package ru.nsu.lupa

import java.io.File
import javax.script.ScriptEngineManager

class ConfigurationProvider {
    fun fromKtsFile(file: File): Configuration {
        val engine = ScriptEngineManager().getEngineByExtension("kts").apply {
            eval("import ru.nsu.lupa.dsl.*\n")
        }
        return engine.eval("config { ${file.readText()} }") as Configuration
    }
}
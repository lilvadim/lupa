package ru.nsu.lupa.names

import org.jsoup.Jsoup
import ru.nsu.lupa.Name
import ru.nsu.lupa.NameProcessor
import java.util.*


class GramotaRuNameSynonyms : NameProcessor {

    private fun shouldParseHTML(name: String): String {
        val doc = Jsoup.connect("http://www.gramota.ru/slovari/info/petr/imsm/").get()
        var a = ""
        try {
            a = doc.select("div:contains($name)").first().parent().children().first().text()
        } catch (e: Exception) {
            println("No such name")
        }
        return a
    }

    private fun parseString(name: String): Set<String> {
        val stringToParse = shouldParseHTML(name)
        var newStr = stringToParse.substring(936, 74533)
        newStr = newStr.lowercase(Locale.getDefault())
        val nameList = newStr.split(" ").toMutableList()

        val res = mutableSetOf<String>()
        if (nameList.contains("$name:")) {
            var n = nameList[nameList.indexOf("$name:")]
            var nameIndexPos = nameList.indexOf("$name:")
            var nameIndexNeg = nameList.indexOf("$name:")
            res.add(n.substring(0, n.length - 1))

            while (n[n.length - 1] != ';') {
                nameIndexPos++
                n = nameList[nameIndexPos]
                res.add(n.substring(0, n.length - 1))

            }
            n = nameList[nameIndexNeg]
            while (n[n.length - 1] != ';') {
                res.add(n.substring(0, n.length - 1))
                nameIndexNeg--
                n = nameList[nameIndexNeg]
            }
        }

        if (nameList.contains("$name,")) {
            var n = nameList[nameList.indexOf("$name,")]
            var nameIndexPos = nameList.indexOf("$name,")
            var nameIndexNeg = nameList.indexOf("$name,")

            res.add(n.substring(0, n.length - 1))

            while (n[n.length - 1] != ';') {
                nameIndexPos++
                n = nameList[nameIndexPos]
                res.add(n.substring(0, n.length - 1))
            }
            n = nameList[nameIndexNeg]
            while (n[n.length - 1] != ';') {
                res.add(n.substring(0, n.length - 1))
                nameIndexNeg--
                n = nameList[nameIndexNeg]
            }
        }

        if (nameList.contains("$name;")) {
            var n = nameList[nameList.indexOf("$name;")]
            var nameIndexPos = nameList.indexOf("$name;")
            var nameIndexNeg = nameList.indexOf("$name;")
            res.add(n.substring(0, n.length - 1))

            while (n[n.length - 1] != ';') {
                nameIndexPos++
                n = nameList[nameIndexPos]
                res.add(n.substring(0, n.length - 1))
            }
            n = nameList[nameIndexNeg]
            res.add(n.substring(0, n.length - 1))

            while (n[n.length - 1] != ';') {
                nameIndexNeg--
                n = nameList[nameIndexNeg]
                res.add(n.substring(0, n.length - 1))

            }
        }

        return res
    }


    override fun synonymsOf(name: Name): Set<Name> {
        val nameToParse = name.value
        return parseString(nameToParse.lowercase()).map { Name(it) }.toSet()

    }


}


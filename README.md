# lupa

Person Search API. University project.

## Добавление ресурса

Создаем класс в пакете `ru.nsu.lupa.res` и наследуем его от `ru.nsu.lupa.Resource`
или же от `ru.nsu.lupa.Resource.BaseResource`.

Необходимо реализовать следующий метод:

```kotlin
/**
 * Search for user info using given match graph,
 * found information written in same graph
 */
fun performSearch(matchGraph: MatchGraph)
```

В нем передается параметром граф соответствий.
Ваша задача по контракту

- реализовать поиск при помощи API или чего-либо другого,
- обработать результат запроса и сложить его в объект `ru.nsu.lupa.Profile`,
- добавить все найденные профили в граф, вызвав метод `addProfile`
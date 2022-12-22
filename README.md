# lupa

Person Search API. University project.

## Дизайн

![Imgur](https://i.imgur.com/1ECluxE.png)

_Профиль_ - некоторый набор данных, пренадлежащих наверняка одному человеку (то, что данные принадлежат одному человеку,
можно говорить тогда и только тогда, когда мы взяли их с одного условного аккаунта на каком-либо ресурсе).

Изначально пользователь имеет, положим, имя и фамилию и отдает их программе.
Итак, программа совершает итерацию поиска по ресурсу, который позволяет результативно искать по имени и фамилии,
положим, ВКонтакте. ВКонтакте, очевидно, выдаст больше, чем одну страницу с данными именем и фамилией. На странице может
быть найден некоторый никнейм, вписываем его в профиль с теми именем и фамилией. Получаем некоторое множество профилей,
уже имеющих при себе имя, фамилию, никнейм.
На следующей итерации мы можем найти для некоторых таких профилей соответствие, положим, в телеграме, и, положим, что по
никнейму. Но мы не можем утверждать, что человек один и тот же. Поэтому мы строим некоторый граф соответствий, вершины
его - профили, ребра имеют пометки, а именно критерий соответствия.
На каждой следующей итерации граф наполняется новыми профилями. По итогу работы программа проранжирует наиболее богатые
соответствиями цепочки профилей из разных ресурсов, ведь можно полагать, что это один человек, если есть достаточно
соответствий на разных ресурсах.

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

# Использование

## VK

Для поиска ВКонтакте необходимо авторизоваться.

1. Переходим
   по [ссылке](`https://oauth.vk.com/oauth/authorize?client_id=51506122&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends,notify,photos,wall,email,mail,groups,stats,offline&response_type=token&v=5.89`)
2. Даем разрешение
3. Копируем из строки браузера значения параметров `access_token` и `user_id`
   - `..&access_token=<ЗНАЧЕНИЕ>&..`
   - `..&user_id=<ЗНАЧЕНИЕ>&..`
4. В файле конфигурации для VK указываем эти параметры

```kotlin
package ru.nsu.lupa

import ru.nsu.lupa.dsl.*

config {
    profiles {
        profile(
            name = "Иван",
            surname = "Иванов"
        )
    }

    parameters {
        resource("vk") {
            "accessToken" set "ВАШЕ_ЗНАЧЕНИЕ"
            "userId" set "ВАШЕ_ЗНАЧЕНИЕ"
        }
    }
}
```
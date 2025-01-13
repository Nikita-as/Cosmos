package ru.otus.cosmos.feature.planets.data.repository

import ru.otus.cosmos.feature.R
import ru.otus.cosmos.feature.planets.domain.model.PlanetInfo
import ru.otus.cosmos.feature.planets.domain.model.Planets
import ru.otus.cosmos.feature.planets.domain.repository.PlanetsRepository

class PlanetsRepositoryImplMock() : PlanetsRepository {
    override suspend fun getPlanets() = Planets(
        planets = listOf(
            PlanetInfo(
                id = 1,
                icon = R.drawable.ic_sun,
                name = "Солнце",
                info = "Солнце составляет более 99,8% от массы всей Солнечной системы, что делает его центром гравитационного поля, вокруг которого вращаются планеты, астероиды и кометы.",
            ),
            PlanetInfo(
                id = 1,
                icon = R.drawable.ic_venus,
                name = "Венера",
                info = "Венера имеет самый густой и токсичный газообразный слой, состоящий главным образом из углекислого газа, с облаками серной кислоты, из-за чего у нее наблюдается эффект парникового газа, делающий среднюю температуру на поверхности около 462°C.",
            ), PlanetInfo(
                id = 2,
                icon = R.drawable.ic_earth,
                name = "Земля",
                info = "Земля — единственная известная планета, на которой существует жизнь, благодаря наличию воды в жидком состоянии и подходящим климатическим условиям.",
            ), PlanetInfo(
                id = 1,
                icon = R.drawable.ic_mars,
                name = "Марс",
                info = "Марс известен как Красная планета из-за наличия оксида железа (ржавчины) в своей почве, и на его поверхности есть самый большой вулкан в Солнечной системе — Олимп.",
            ), PlanetInfo(
                id = 1,
                icon = R.drawable.ic_jupiter,
                name = "Юпитер",
                info = "Юпитер — самая большая планета в Солнечной системе и имеет самый мощный магнитный полюс, а его знаменитое \"Большое красное пятно\" — это гигантский шторм, который бушует уже более 350 лет.",
            ), PlanetInfo(
                id = 1,
                icon = R.drawable.ic_saturn,
                name = "Сатурн",
                info = "Сатурн известен своими великолепными кольцами, состоящими в основном из льда и частиц камней, и это единственная планета в Солнечной системе, которая может плавать в воде благодаря своей низкой плотности.",
            ), PlanetInfo(
                id = 1,
                icon = R.drawable.ic_uranus,
                name = "Уран",
                info = "Уран — это \"наклоненная\" планета, которая вращается на боку, с осью наклона 98°, что приводит к необычному изменению сезонов, где полюса находятся под прямым солнечным светом в течение 42 лет.",
            ), PlanetInfo(
                id = 1,
                icon = R.drawable.ic_neptune,
                name = "Нептун",
                info = "Нептун — самая дальняя от Солнца планета и известен своими интенсивными ветрами, которые могут достигать скорости более 2,000 км/ч, что делает их самыми быстрыми в Солнечной системе.",
            )
        )
    )
}
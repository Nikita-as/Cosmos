package ru.otus.cosmos.feature.photo_day.data.repository

import kotlinx.coroutines.delay
import ru.otus.cosmos.feature.photo_day.domain.model.PhotoDay
import ru.otus.cosmos.feature.photo_day.domain.repository.PhotoDayRepository

class PhotoDayRepositoryImplMock : PhotoDayRepository {
    override suspend fun getPhotoDay(): PhotoDay {
        delay(500)
        return PhotoDay(
            image = "",
            title = "Methane Bubbles Frozen in Lake Baikal",
            explanation = "What are these bubbles frozen into Lake Baikal? Methane.  Lake Baikal, a UNESCO World Heritage Site in Russia, is the world's largest (by volume), oldest, and deepest lake, containing over 20% of the world's fresh water. The lake is also a vast storehouse of methane, a greenhouse gas that, if released, could potentially increase the amount of infrared light absorbed by Earth's atmosphere, and so increase the average temperature of the entire planet. Fortunately, the amount of methane currently bubbling out is not climatologically important. It is not clear what would happen, though, were temperatures to significantly increase in the region, or if the water level in Lake Baikal were to drop.  Pictured, bubbles of rising methane froze during winter into the exceptionally clear ice covering the lake.   Jigsaw Challenge: Astronomy Puzzle of the Day)"
        )
    }
}
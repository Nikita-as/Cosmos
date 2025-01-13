package ru.otus.cosmos.feature.photo_day.repository

import com.google.common.truth.Truth.assertThat
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever
import ru.otus.cosmos.common_base.shared_preference.SharedPreferencesMediator
import ru.otus.cosmos.common_base.utils.getCurrentDate
import ru.otus.cosmos.common_storage.database.dao.PhotoDayDao
import ru.otus.cosmos.feature.photo_day.data.api.PhotoDayApi
import ru.otus.cosmos.feature.photo_day.data.mapper.PhotoDayMapper
import ru.otus.cosmos.feature.photo_day.data.model.PhotoDayResponse
import ru.otus.cosmos.feature.photo_day.data.repository.PhotoDayRepositoryImpl
import ru.otus.cosmos.feature.photo_day.domain.model.PhotoDay
import ru.otus.cosmos.common_test.mock.DispatcherProviderMock
import ru.otus.cosmos.feature.photo_day.repository.utils.MainCoroutinesRule
import ru.otus.cosmos.common_test.mock.SharedPreferencesFakeImpl

class PhotoDayRepositoryTest {

    @get:Rule
    val mainCoroutinesRule = MainCoroutinesRule()

    @RelaxedMockK
    private lateinit var api: PhotoDayApi

    private lateinit var mapper: PhotoDayMapper
    private lateinit var repository: PhotoDayRepositoryImpl
    private lateinit var dao: PhotoDayDao
    private lateinit var photoDayPreferenceMediator: SharedPreferencesMediator

    @Before
    fun setUp() {
        MockKAnnotations.init(
            this,
            relaxed = true,
            relaxUnitFun = true,
            overrideRecordPrivateCalls = false
        )
        photoDayPreferenceMediator = SharedPreferencesFakeImpl()
        dao = mock()
        mapper = PhotoDayMapper()
        repository = PhotoDayRepositoryImpl(
            api,
            mapper,
            DispatcherProviderMock(),
            dao,
            photoDayPreferenceMediator
        )
    }

    @Test
    fun `При совпадающей дате в shared и текущей, данные берутся из room`() = runTest {
        photoDayPreferenceMediator.currentDate = getCurrentDate()
        whenever(dao.getPhotoDayInfo()).thenReturn(mapper.map(getPhotoDay()))
        val response = repository.getPhotoDay()
        verify(dao).getPhotoDayInfo()
        assertNotNull(response)
    }

    @Test
    fun `При несовпадающей дате в shared и текущей, данные берутся из api и сохраняются в room`() =
        runTest {
            photoDayPreferenceMediator.currentDate = LOCAL_DATE
            coEvery { api.getImageOfTheDay(any()) }.coAnswers { getPhotoDayResponse() }
            val photoDay = mapper.map(getPhotoDayResponse())
            val response = repository.getPhotoDay()
            assertNotNull(response)
            assertThat(response).isEqualTo(photoDay)
            verify(dao).insert(mapper.map(response))
        }

    @Test
    fun `При отсутсвующей дате в shared данные берутся из api и сохраняются в room`() = runTest {
        coEvery { api.getImageOfTheDay(any()) }.coAnswers { getPhotoDayResponse() }
        val photoDay = mapper.map(getPhotoDayResponse())
        val response = repository.getPhotoDay()
        assertNotNull(response)
        assertThat(response).isEqualTo(photoDay)
        verify(dao).insert(mapper.map(response))
    }

    @Test
    fun `Проверка сохранения данных в shared`() = runTest {
        coEvery { api.getImageOfTheDay(any()) }.coAnswers { getPhotoDayResponse() }
        assertThat(photoDayPreferenceMediator.currentDate).isEmpty()
        repository.getPhotoDay()
        assertThat(photoDayPreferenceMediator.currentDate).isEqualTo(getCurrentDate())
    }

    private fun getPhotoDay() = PhotoDay(
        title = "Байкал",
        image = "",
        explanation = "Самое глубокое озеро"
    )

    private fun getPhotoDayResponse() = PhotoDayResponse(
        title = "Байкал",
        url = "",
        explanation = "Самое глубокое озеро"
    )

    companion object {
        private const val LOCAL_DATE = "2025-09-01"
    }
}
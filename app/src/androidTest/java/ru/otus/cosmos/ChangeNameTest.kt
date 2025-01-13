package ru.otus.cosmos

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.otus.cosmos.ui.MainActivity

@HiltAndroidTest
class ChangeNameTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Before
    fun setup() = hiltRule.inject()

    @Test
    fun changeNameUiTest() {
        composeTestRule.apply {
            onNodeWithText("Профиль").performClick()
            onNodeWithText("Профиль").assertIsSelected()
            onNodeWithContentDescription("Edit").performClick()
            composeTestRule.onNodeWithTag("TextField").assertIsDisplayed()
        }
    }
}
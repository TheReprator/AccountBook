package dev.reprator.khatabook.screens.home

import androidx.compose.runtime.Composable
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import dev.reprator.khatabook.screens.splash.SplashScreen
import io.github.xxfast.decompose.router.Router
import io.github.xxfast.decompose.router.content.RoutedContent
import io.github.xxfast.decompose.router.rememberRouter

@Composable
fun HomeScreen() {

  val router: Router<StoryHomeScreen> =
    rememberRouter(StoryHomeScreen::class, initialStack = {
      listOf(StoryHomeScreen.List)
    })

  RoutedContent(
    router = router,
    animation = stackAnimation(slide())
  ) { screen ->
    when (screen) {
      StoryHomeScreen.List -> SplashScreen()
    }
  }
}

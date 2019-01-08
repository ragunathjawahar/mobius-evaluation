package mobius.demo.login

import com.spotify.mobius.test.FirstMatchers
import com.spotify.mobius.test.InitSpec
import com.spotify.mobius.test.InitSpec.assertThatFirst
import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class LoginLogicTest {
  private val initSpec = InitSpec<LoginModel, Nothing>(LoginLogic.init())
  private val updateSpec = UpdateSpec<LoginModel, LoginEvent, Nothing>(LoginLogic::update)

  @Test
  fun `user starts with blank model`() {
    initSpec
        .`when`(LoginModel.BLANK)
        .then(
            assertThatFirst(
                FirstMatchers.hasModel(LoginModel.BLANK),
                FirstMatchers.hasNoEffects()
            )
        )
  }

  @Test
  fun `user can restore last known model`() {
    val lastKnownModel = LoginModel.BLANK.enterEmail("jake@wharton.in")

    initSpec
        .`when`(lastKnownModel)
        .then(
            assertThatFirst(
                FirstMatchers.hasModel(lastKnownModel),
                FirstMatchers.hasNoEffects()
            )
        )
  }

  @Test
  fun `user can enter email`() {
    val blankModel = LoginModel.BLANK
    val email = "joe@whatever.com"

    updateSpec
        .given(blankModel)
        .`when`(EnterEmailEvent(email))
        .then(
            assertThatNext(
                hasModel(blankModel.enterEmail(email)),
                hasNoEffects()
            )
        )
  }

  @Test
  fun `user can enter password`() {
    val blankModel = LoginModel.BLANK
    val password = "secret-password"

    updateSpec
        .given(blankModel)
        .`when`(EnterPasswordEvent(password))
        .then(
            assertThatNext(
                hasModel(blankModel.enterPassword(password)),
                hasNoEffects()
            )
        )
  }
}

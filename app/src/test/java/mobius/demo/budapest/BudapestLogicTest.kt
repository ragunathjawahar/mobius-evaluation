package mobius.demo.budapest

import com.spotify.mobius.test.FirstMatchers
import com.spotify.mobius.test.InitSpec
import com.spotify.mobius.test.InitSpec.assertThatFirst
import com.spotify.mobius.test.NextMatchers.hasModel
import com.spotify.mobius.test.NextMatchers.hasNoEffects
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class BudapestLogicTest {
  private val initSpec = InitSpec<BudapestModel, Nothing>(BudapestLogic.init())
  private val updateSpec = UpdateSpec<BudapestModel, BudapestEvent, Nothing>(BudapestLogic::update)

  @Test
  fun `start with stranger`() {
    initSpec
        .`when`(BudapestModel.STRANGER)
        .then(
            assertThatFirst(
                FirstMatchers.hasModel(BudapestModel.STRANGER),
                FirstMatchers.hasNoEffects()
            )
        )
  }

  @Test
  fun `restore last known model`() {
    val lastKnownModel = BudapestModel("whatever")

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
  fun `user can enter a name`() {
    val name = "Roger"

    updateSpec
        .given(BudapestModel.STRANGER)
        .`when`(EnterNameEvent(name))
        .then(
            assertThatNext(
                hasModel(BudapestModel(name)),
                hasNoEffects()
            )
        )
  }

  @Test
  fun `user can delete name`() {
    val tomBudapestModel = BudapestModel
        .STRANGER
        .enterName("Tom")

    updateSpec
        .given(tomBudapestModel)
        .`when`(DeleteNameEvent)
        .then(
            assertThatNext(
                hasModel(BudapestModel.STRANGER),
                hasNoEffects()
            )
        )
  }
}

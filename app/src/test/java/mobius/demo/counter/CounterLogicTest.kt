package mobius.demo.counter

import com.spotify.mobius.test.FirstMatchers
import com.spotify.mobius.test.InitSpec
import com.spotify.mobius.test.InitSpec.assertThatFirst
import com.spotify.mobius.test.NextMatchers.*
import com.spotify.mobius.test.UpdateSpec
import com.spotify.mobius.test.UpdateSpec.assertThatNext
import org.junit.Test

class CounterLogicTest {
  private val initSpec = InitSpec<CounterModel, CounterEffect>(CounterLogic.init())
  private val updateSpec = UpdateSpec<CounterModel, CounterEvent, CounterEffect>(CounterLogic::update)

  @Test
  fun `counter starts with zero`() {
    initSpec
        .`when`(CounterModel.ZERO)
        .then(
            assertThatFirst(
                FirstMatchers.hasModel(CounterModel.ZERO),
                FirstMatchers.hasNoEffects()
            )
        )
  }

  @Test
  fun `counter can resume state`() {
    initSpec
        .`when`(CounterModel(5))
        .then(
            assertThatFirst(
                FirstMatchers.hasModel(CounterModel(5)),
                FirstMatchers.hasNoEffects()
            )
        )
  }

  @Test
  fun `user can increment counter value`() {
    updateSpec
        .given(CounterModel.ZERO)
        .`when`(IncrementCounterEvent)
        .then(
            assertThatNext(
                hasModel(CounterModel.ZERO.increment()),
                hasNoEffects()
            )
        )
  }

  @Test
  fun `user can decrement counter value`() {
    updateSpec
        .given(CounterModel.ZERO)
        .`when`(DecrementCounterEvent)
        .then(
            assertThatNext(
                hasModel(CounterModel.ZERO.decrement()),
                hasNoEffects()
            )
        )
  }

  @Test
  fun `user can multiply by 5`() {
    updateSpec
        .given(CounterModel(2))
        .`when`(MultiplyByEvent(5))
        .then(
            assertThatNext(
                hasModel(CounterModel(10)),
                hasNoEffects()
            )
        )
  }

  @Test
  fun `show toast if the counter value is a multiple of 3`() {
    updateSpec
        .given(CounterModel(5))
        .`when`(IncrementCounterEvent)
        .then(
            assertThatNext(
                hasModel(CounterModel(6)),
                hasEffects(ShowMultipleOf3ToastEffect as CounterEffect)
            )
        )
  }
}

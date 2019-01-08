package mobius.demo.counter.effecthandlers

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import mobius.demo.counter.ShowMultipleOf3ToastEffect
import mobius.demo.test.EffectHandlerTestCase
import mobius.demo.test.ImmediateSchedulersProvider
import org.junit.Test

class CounterEffectHandlerTest {
  @Test
  fun `it can show a multiple of 3 toast`() {
    // given
    val viewActions = mock<CounterViewActions>()
    val schedulersProvider = ImmediateSchedulersProvider()
    val effectHandler = CounterEffectHandler.createEffectHandler(viewActions, schedulersProvider)
    val testCase = EffectHandlerTestCase(effectHandler)

    // when
    testCase.dispatchEffect(ShowMultipleOf3ToastEffect)

    // then
    testCase.assertNoOutgoingEvents()
    verify(viewActions).showMultipleOf3Toast()
  }
}

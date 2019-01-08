package mobius.demo.counter.effecthandlers

import com.spotify.mobius.rx2.RxMobius
import io.reactivex.ObservableTransformer
import mobius.demo.architecture.threading.SchedulersProvider
import mobius.demo.counter.CounterEffect
import mobius.demo.counter.CounterEvent
import mobius.demo.counter.ShowMultipleOf3ToastEffect

object CounterEffectHandler {
  fun createEffectHandler(
      viewActions: CounterViewActions,
      schedulersProvider: SchedulersProvider
  ): ObservableTransformer<CounterEffect, CounterEvent> {
    return RxMobius
        .subtypeEffectHandler<CounterEffect, CounterEvent>()
        .addAction(ShowMultipleOf3ToastEffect::class.java, viewActions::showMultipleOf3Toast, schedulersProvider.ui)
        .build()
  }
}

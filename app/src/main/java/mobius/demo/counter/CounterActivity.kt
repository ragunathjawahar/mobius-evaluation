package mobius.demo.counter

import android.widget.Toast
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_counter.*
import mobius.demo.R
import mobius.demo.architecture.BaseActivity
import mobius.demo.architecture.ViewRenderer
import mobius.demo.architecture.threading.DefaultSchedulersProvider
import mobius.demo.counter.effecthandlers.CounterEffectHandler
import mobius.demo.counter.effecthandlers.CounterViewActions

class CounterActivity :
    BaseActivity<CounterModel, CounterEvent, CounterEffect>(),
    CounterViewActions,
    CounterView {
  override fun layoutResId(): Int =
      R.layout.activity_counter

  override fun setup() {
    incrementButton.setOnClickListener { eventSource.notifyEvent(IncrementCounterEvent) }
    decrementButton.setOnClickListener { eventSource.notifyEvent(DecrementCounterEvent) }
    multiplyBy5Button.setOnClickListener { eventSource.notifyEvent(MultiplyByEvent(5)) }
  }

  override fun initialModel(): CounterModel =
      CounterModel.ZERO

  override fun updateFunction(
      model: CounterModel,
      event: CounterEvent
  ): Next<CounterModel, CounterEffect> =
      CounterLogic.update(model, event)

  override fun viewRenderer(): ViewRenderer<CounterModel, CounterView> =
      CounterViewRenderer(this)

  override fun effectHandler(): ObservableTransformer<CounterEffect, CounterEvent> =
      CounterEffectHandler.createEffectHandler(this, DefaultSchedulersProvider())

  override fun setCounter(value: Int) {
    counterTextView.text = value.toString()
  }

  override fun showMultipleOf3Toast() {
    Toast
        .makeText(this, "That's a multiple of 3!", Toast.LENGTH_SHORT)
        .show()
  }
}

package mobius.demo.counter

import mobius.demo.architecture.ViewRenderer

class CounterViewRenderer(
    view: CounterView
) : ViewRenderer<CounterModel, CounterView>(view) {
  override fun render(model: CounterModel) {
    view.setCounter(model.counter)
  }
}

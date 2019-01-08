package mobius.demo.counter

sealed class CounterEvent

object IncrementCounterEvent: CounterEvent()

object DecrementCounterEvent: CounterEvent()

data class MultiplyByEvent(val multiplier: Int) : CounterEvent()

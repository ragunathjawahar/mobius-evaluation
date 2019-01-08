package mobius.demo.counter

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class CounterModel(
    val counter: Int
) : Parcelable {
  companion object {
    val ZERO = CounterModel(0)
  }

  fun increment(): CounterModel =
      copy(counter = counter + 1)

  fun decrement(): CounterModel =
      copy(counter = counter - 1)

  fun multiplyBy(multiplier: Int): CounterModel =
      copy(counter = counter * multiplier)
}

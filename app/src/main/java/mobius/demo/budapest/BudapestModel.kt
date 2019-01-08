package mobius.demo.budapest

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BudapestModel(
    val name: String
) : Parcelable {
  companion object {
    val STRANGER = BudapestModel("")
  }

  fun enterName(name: String): BudapestModel =
      copy(name = name)

  fun deleteName(): BudapestModel =
      STRANGER
}

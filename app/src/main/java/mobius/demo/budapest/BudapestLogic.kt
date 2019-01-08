package mobius.demo.budapest

import com.spotify.mobius.First
import com.spotify.mobius.Init
import com.spotify.mobius.Next
import com.spotify.mobius.Next.next

object BudapestLogic {
  fun init(): Init<BudapestModel, Nothing> =
      Init { First.first(it) }

  fun update(
      model: BudapestModel,
      event: BudapestEvent
  ): Next<BudapestModel, Nothing> = when (event) {
    is EnterNameEvent -> next(model.enterName(event.name))
    is DeleteNameEvent -> next(model.deleteName())
  }
}

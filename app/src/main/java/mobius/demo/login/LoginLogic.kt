package mobius.demo.login

import com.spotify.mobius.First
import com.spotify.mobius.Init
import com.spotify.mobius.Next
import com.spotify.mobius.Next.next

object LoginLogic {
  fun init(): Init<LoginModel, Nothing> =
      Init { First.first(it) }

  fun update(
      model: LoginModel,
      event: LoginEvent
  ): Next<LoginModel, Nothing> {
    return when (event) {
      is EnterEmailEvent -> next(model.enterEmail(event.email))
      is EnterPasswordEvent -> next(model.enterPassword(event.password))
      else -> TODO()
    }
  }
}

package mobius.demo.login

import arrow.core.None
import arrow.core.Option
import arrow.core.Some
import mobius.demo.login.domain.Email
import mobius.demo.login.domain.Password

data class LoginModel(
    val email: Option<Email>,
    val password: Option<Password>
) {
  companion object {
    val BLANK = LoginModel(None, None)
  }

  fun enterEmail(email: String): LoginModel =
      copy(email = Some(Email(email)))

  fun enterPassword(password: String): LoginModel =
      copy(password = Some(Password(password)))
}

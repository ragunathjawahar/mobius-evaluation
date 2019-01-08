package mobius.demo.login.domain

data class Email(
    val value: String
) {

  /** This email validation is a proof of my inability to write good code or jokes. I suck at both. */
  val isValid: Boolean
    get() {
      val indexOfDot = value.lastIndexOf('.')
      val indexOfAt = value.indexOf('@')
      return indexOfDot != -1 && indexOfAt != -1 && indexOfDot > indexOfAt
    }
}

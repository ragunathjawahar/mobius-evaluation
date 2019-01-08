package mobius.demo.login.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PasswordTest {
  @Test
  fun `passwords with less than 8 characters are invalid`() {
    val shortPassword = Password("123")

    assertThat(shortPassword.isValid)
        .isFalse()
  }

  @Test
  fun `passwords with 8 characters are valid`() {
    val validPassword = Password("12345678")

    assertThat(validPassword.isValid)
        .isTrue()
  }

  @Test
  fun `passwords with more than 8 characters are valid`() {
    val longPassword = Password("123456789")

    assertThat(longPassword.isValid)
        .isTrue()
  }
}

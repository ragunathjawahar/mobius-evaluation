package mobius.demo.login.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class EmailTest {
  @Test
  fun `empty email is invalid`() {
    val emptyEmail = Email("")

    assertThat(emptyEmail.isValid)
        .isFalse()
  }

  @Test
  fun `valid emails have a dot followed by @`() {
    val validEmail = Email("joe@msn.in")

    assertThat(validEmail.isValid)
        .isTrue()
  }

  @Test
  fun `valid emails can have dots before @`() {
    val validEmail = Email("joe.birch@msn.in")

    assertThat(validEmail.isValid)
        .isTrue()
  }
}

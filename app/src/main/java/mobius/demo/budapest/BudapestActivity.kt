package mobius.demo.budapest

import android.text.Editable
import com.spotify.mobius.Next
import io.reactivex.ObservableTransformer
import kotlinx.android.synthetic.main.activity_budapest.*
import mobius.demo.R
import mobius.demo.architecture.BaseActivity
import mobius.demo.architecture.ViewRenderer
import mobius.demo.architecture.effecthandlers.NoOpEffectHandler

class BudapestActivity : BaseActivity<BudapestModel, BudapestEvent, Nothing>(), BudapestView {
  override fun layoutResId(): Int =
      R.layout.activity_budapest

  override fun setup() {
    setNameTextChangeListener()
  }

  override fun initialModel(): BudapestModel =
      BudapestModel.STRANGER

  override fun updateFunction(
      model: BudapestModel,
      event: BudapestEvent
  ): Next<BudapestModel, Nothing> =
      BudapestLogic.update(model, event)

  override fun viewRenderer(): ViewRenderer<BudapestModel, BudapestView> =
      BudapestViewRenderer(this)

  override fun effectHandler(): ObservableTransformer<Nothing, BudapestEvent> =
      NoOpEffectHandler()

  override fun greetStranger() {
    greetingTextView.text = getString(R.string.hello_stranger)
  }

  override fun greet(name: String) {
    greetingTextView.text = getString(R.string.hello_user, name)
  }

  private fun setNameTextChangeListener() {
    nameEditText.addTextChangedListener(object : TextWatcherAdapter() {
      override fun afterTextChanged(s: Editable?) {
        if (s == null) return

        val eventToSend = if (s.isNullOrBlank()) {
          DeleteNameEvent
        } else {
          EnterNameEvent(s.toString())
        }
        eventSource.notifyEvent(eventToSend)
      }
    })
  }
}

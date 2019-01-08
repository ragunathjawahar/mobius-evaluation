package mobius.demo.test

import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import mobius.demo.architecture.threading.SchedulersProvider

class ImmediateSchedulersProvider : SchedulersProvider {
  override val io: Scheduler
    get() = Schedulers.trampoline()

  override val ui: Scheduler
    get() = Schedulers.trampoline()

  override val computation: Scheduler
    get() = Schedulers.trampoline()
}

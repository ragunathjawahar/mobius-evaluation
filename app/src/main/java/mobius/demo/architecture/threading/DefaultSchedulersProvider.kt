package mobius.demo.architecture.threading

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class DefaultSchedulersProvider : SchedulersProvider {
  override val io: Scheduler
    get() = Schedulers.io()

  override val ui: Scheduler
    get() = AndroidSchedulers.mainThread()

  override val computation: Scheduler
    get() = Schedulers.computation()
}

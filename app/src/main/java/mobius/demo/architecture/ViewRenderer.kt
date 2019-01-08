package mobius.demo.architecture

abstract class ViewRenderer<M, V>(protected val view: V) {
  abstract fun render(model: M)
}

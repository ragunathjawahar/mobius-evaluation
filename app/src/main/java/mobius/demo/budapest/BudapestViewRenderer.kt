package mobius.demo.budapest

import mobius.demo.architecture.ViewRenderer

class BudapestViewRenderer(
    view: BudapestView
) : ViewRenderer<BudapestModel, BudapestView>(view) {
  override fun render(model: BudapestModel) {
    if (model == BudapestModel.STRANGER) {
      view.greetStranger()
    } else {
      view.greet(model.name)
    }
  }
}

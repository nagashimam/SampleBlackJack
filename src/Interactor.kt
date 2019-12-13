class Interactor(private val presenter: Presenter) {
    private val human = Human()
    private val deck = Deck()

    // 得点を表示する
    fun showScore() {
        presenter.showMessage("現在の得点:\n${human.calculateScore()}")
        presenter.promptInput()
    }

    // 手札を表示する
    fun showHands() {
        presenter.showMessage("現在の手札:\n${human.handsToString()}")
        presenter.promptInput()
    }

    // 手札を引く
    fun draw() {
        presenter.showMessage(human.draw(deck))
        if (human.isOver()) {
            presenter.showMessage("現在の得点:\n${human.calculateScore()}\nあなたの負けです。")
        } else {
            presenter.promptInput()
        }
    }

    // プレーヤーが引くのをやめ、コンピューターの分を引く
    fun stopDrawing() {
        presenter.showMessage("もう引きません")
        presenter.promptInput()
    }

}
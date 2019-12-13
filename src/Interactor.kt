class Interactor(private val presenter: Presenter) {
    private val human = Human()
    private val deck = Deck()

    // 得点を表示する
    fun showScore() {
        presenter.showMessage("現在の得点を見ます")
        presenter.promptInput()
    }

    // 手札を表示する
    fun showHands() {
        presenter.showMessage("現在の手札を見ます")
        presenter.promptInput()
    }

    // 手札を引く
    fun draw() {
        presenter.showMessage(human.draw(deck))
        presenter.promptInput()
    }

    // プレーヤーが引くのをやめ、コンピューターの分を引く
    fun stopDrawing() {
        presenter.showMessage("もう引きません")
        presenter.promptInput()
    }

}
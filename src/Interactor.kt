class Interactor(private val presenter: Presenter) {
    private val human = Human()
    private val deck = Deck()
    private val computer = Computer()

    // 初期化処理
    fun setUp() {
        with(presenter) {
            showMessage(human.draw(deck))
            showMessage(human.draw(deck))
            showMessage(computer.draw(deck))
            showMessage(computer.draw(deck))
            promptInput()
        }
    }

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
        // 表示していなかったコンピューターの2枚目の手札を表示
        presenter.showMessage(computer.flipSecondCard())
        // 上限を超えるまでコンピューターが手札を引く
        computer.drawUntilLimit(deck).forEach { result -> presenter.showMessage(result) }

        // それぞれ最終得点を表示
        showFinalScore(computer)
        showFinalScore(human)

        // 結果を表示
        presenter.showMessage(
            if (isHumanWin()) {
                "あなたの勝ちです。"
            } else {
                "あなたの負けです。"
            }
        )
    }

    // 人間が勝ったかどうかを判定
    private fun isHumanWin(): Boolean {
        return computer.calculateScore().let { computerScore ->
            when {
                computerScore > 21 -> true
                else -> computerScore < human.calculateScore()
            }
        }
    }

    // プレーヤーの最終得点を表示
    private fun showFinalScore(player: Player) {
        presenter.showMessage("${player.whichSide()}の最終得点:${player.calculateScore()}")
    }

}
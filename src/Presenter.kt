/**
 * 出入力と実際の処理を仲立ちするクラス
 */
class Presenter(private val view: View) {
    private val interactor = Interactor(this)

    // 入力をIOから受け取る
    fun receiveInput(input: String) {
        when (input) {
            "0" -> interactor.showScore()
            "1" -> interactor.showHands()
            "2" -> interactor.draw()
            "3" -> interactor.stopDrawing()
            else -> view.promptInput()
        }
    }

    // 入力を促す
    fun promptInput() = view.promptInput()

    // メッセージを表示する
    fun showMessage(msg: String) = view.showMessage(msg)
}

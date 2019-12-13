/**
 * 入出力を担当するクラス
 */
class View {
    private val presenter = Presenter(this)

    // 入力をしてもらうためのメッセージを表示する
    // 入力は自分では処理せず、presenterに投げる
    fun promptInput() {
        println("カードを引きますか?\n0:現在の点数を見る 1:現在の手札を見る 2:引く 3:もう引かない")
        readLine()
            ?.let { presenter.receiveInput(it) }
            ?: let {
                println("すみません、入力が読み込めませんでした")
                promptInput()
            }
    }

    // コンソールにメッセージを表示する
    // グラフィックを付ける際には、Javascriptでいうalert()にする
    fun showMessage(msg : String) = println(msg)
}

fun main() {
    View().promptInput()
}

/**
 * 出入力と実際の処理を仲立ちするクラス
 */
class Presenter(private val view: View) {

    // 入力をIOから受け取る
    fun receiveInput(input: String) {
        val msg = when (input) {
            "0" -> "現在の得点を見ます"
            "1" -> "現在の手札を見ます"
            "2" -> "手札を引きます"
            "3" -> "もう引きません"
            else -> view.promptInput()
        }

        println(msg)
    }
}
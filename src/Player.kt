import java.lang.StringBuilder

/**
 * Player(コンピューター又は人間)を表すクラス
 *
 * sealedを使うと、他のファイルで使えなくなる
 * どうしてこの書き方をしたかはwhichSide()内のコメントを参照
 */
sealed class Player {
    // 手札
    protected val hands = mutableListOf<Card>()

    // 手札にカードを追加し、結果を文字列として返す
    // グラフィックを付けるときには、返す文字列を画像のパスにする
    fun draw(deck: Deck): String {
        return deck.drawCard().let { card ->
            hands.add(card)
            drawResult(card)
        }
    }

    // カードを引いた結果
    abstract fun drawResult(card: Card): String

    // 手札の合計点を計算
    fun calculateScore(): Int {
        // 手札(カードのリスト)をそれぞれの得点のリストに変換してから合計
        return hands.map { hand -> hand.number.score }.sum()
    }

    // 人間なのか、コンピューターなのかを文字列で返す
    // コンソールでの表示に使う予定
    fun whichSide(): String {
        return when (getInstance()) {
            // when は、全ての分岐をカバーする必要がある
            // Playerはsealedなので、子クラスはこのファイルで定義されているHumanかComputerしかない
            is Human -> "あなた"
            is Computer -> "コンピューター"
            // else -> "?????" ←sealedにしないと、意味のない分岐を書く必要がある
        }
    }

    // 手札の合計点が最大値を超えているのかどうかを返す
    fun isOver(): Boolean = calculateScore() > maxValue()

    // 自分を返す(コンピューター or 人間)
    protected abstract fun getInstance(): Player

    // 手札の合計点を返す
    protected abstract fun maxValue(): Int
}

class Human : Player() {
    // 自分を返す(人間)
    override fun getInstance() = this

    // 最大値を返す
    override fun maxValue() = 21

    // カードを引いた結果を返す
    override fun drawResult(card: Card) = "${whichSide()}のカード:$card"

    // 手札を文字列に変える
    // 文字列を直接足し算をすると、遅くなる。一旦StringBuilderに変換して、それから文字列にする
    fun handsToString() = handsToStringBuilder().toString()

    // 手札をStringBuilderに変換する
    private fun handsToStringBuilder() = StringBuilder().apply { hands.forEach { hand -> append("${hand}\n") } }
}

class Computer : Player() {
    // 自分を返す(コンピューター)
    override fun getInstance() = this

    // 最大値を返す
    override fun maxValue() = 17

    // カードを引いた結果を返す
    override fun drawResult(card: Card): String {
        // 2枚目に引いたカードは、最初は表示しない
        val tmp = if (hands.size == 2) {
            "分かりません"
        } else {
            "$card"
        }
        return "${whichSide()}のカード:$tmp"
    }

    // 最後に2枚目のカードがどれだったのかを表示する
    fun flipSecondCard() = hands[1].toString()
}
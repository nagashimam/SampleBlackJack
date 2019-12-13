/**
 * トランプのマークを表すEnum
 */
enum class Suit() {
    CLUB(),
    SPADE(),
    DIAMOND(),
    HEART();
}

/**
 * トランプの数字とその得点を表すEnum
 */
enum class Number(val score: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    ELEVEN(10),
    TWELVE(10),
    THIRTEEN(10);
}

/**
 * SuitとNumberを保持するクラス
 */
class Card(val suit: Suit, val number: Number) {
    override fun toString(): String = "${suit}の${number}"
}

/**
 * トランプの山札を表すクラス
 */
class Deck {

    // 山札
    private val deck: MutableList<Card> by lazy { initializeDeck() }

    // 52枚の山札を作る
    private fun initializeDeck(): MutableList<Card> {
        // 空のリストを用意して、それに対してapply()の中に書いた処理を実行
        val sortedDeck = mutableListOf<Card>()
            .apply {
                // 各マーク毎に、1~13までリストに追加
                Suit.values().forEach { suit ->
                    Number.values().forEach { number ->
                        add(Card(suit, number))
                    }
                }
            }

        // そのままだと順番通りになっているので、シャッフルして返す
        return sortedDeck.apply {
            shuffle()
        }
    }

// initializeDeck()は下のように書いてもいい。参考までに
//    /**
//     * MutableList<Card>(中身を追加/削除できるCardのリスト)が自分自身に対して行う処理
//     *
//     * トランプのマークを受け取り、そのマークの1～13までのカードを自分自身に追加
//     * 例: 引数がCLUB→CLUBの1、CLUBの2、CLUBの3、...CLUBの13を自分自身に追加
//     *
//     * 引数: トランプのマーク
//     * 返り値: なし
//     */
//    private fun MutableList<Card>.addCardsOf(suit: Suit) =
//        Number.values().forEach { number -> add(Card(suit, number)) }
//
//    /**
//     * 52枚のランダムな順番のトランプの山札を作る
//     *
//     * 処理は各行のコメントを参照
//     *
//     * 引数: なし
//     * 返り値: 52枚のトランプの山札
//     */
//    private fun initializeDeck(): MutableList<Card> {
//        // 全てのマークの配列を取得し、以下の処理を行った結果を返す
//        return Suit.values()
//            // 上記の配列の、foldというメソッドを実行
//            // foldの第2引数は、配列の各要素に対して行う処理とその結果を入れる変数の名前
//            // 第1引数は結果を入れる変数の初期値
//            .fold(mutableListOf<Card>(), { acc, suit -> acc.apply { addCardsOf(suit) } })
//            // foldの結果、順番通りにそろっている山札ができるので、シャッフルする
//            .apply { shuffle() }
//    }

}

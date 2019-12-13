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

fun main() {
    println(Card(Suit.CLUB, Number.ONE))
}
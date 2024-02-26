package misezan

import org.scalatest.funsuite.AnyFunSuite
import org.scalatest.matchers.should.Matchers

class MisezanTest extends AnyFunSuite with Matchers {
  import misezan.Misezan.{mise, Aux}
  import shapeless.Nat._

  test("基本: 同じ数字同士を見せ合うと眼は0になる。（自分と同じ格好の人を見ると気まずくなり2人とも立ち去るため。）") {
    implicitly[Aux[_1, _1, _0]]
    implicitly[Aux[_3, _3, _0]]

    mise(_1, _1).toInt shouldBe 0
    mise(_3, _3).toInt shouldBe 0
  }

  test("基本: 小さい数字に大きい数字を見せ合うと大きい数字が残る。（小さい数が大きい数を怖がって逃げてしまうため。）") {
    implicitly[Aux[_1, _2, _2]]
    implicitly[Aux[_2, _1, _2]]
    implicitly[Aux[_3, _0, _3]]

    mise(_1, _2).toInt shouldBe 2
    mise(_2, _1).toInt shouldBe 2
    mise(_3, _0).toInt shouldBe 3
  }
}

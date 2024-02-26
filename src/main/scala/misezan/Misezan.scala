package misezan

import shapeless.Witness
import shapeless.Nat
import shapeless.Nat._
import shapeless.ops.nat.{GT, LT}

object Misezan {
  def mise[N <: Nat](x: Nat, y: Nat)(implicit
      gan: Aux[x.N, y.N, N],
      wn: Witness.Aux[N]
  ): N = wn.value

  trait Gan[N <: Nat, M <: Nat] { type Out <: Nat }
  type Aux[X <: Nat, Y <: Nat, Z <: Nat] =
    Gan[X, Y] { type Out = Z }

  object Gan {
    implicit def ganLt[X <: Nat, Y <: Nat](implicit
        cond: LT[X, Y]
    ): Aux[X, Y, Y] =
      new Gan[X, Y] { type Out = Y }

    implicit def ganGt[X <: Nat, Y <: Nat](implicit
        cond: GT[X, Y]
    ): Aux[X, Y, X] =
      new Gan[X, Y] { type Out = X }

    implicit def ganEq[X <: Nat]: Aux[X, X, _0] =
      new Gan[X, X] { type Out = _0 }
  }
}

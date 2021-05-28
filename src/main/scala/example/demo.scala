package example

import cats.effect.kernel.Async2

object demo {

  def test1[F[_]: Async2]: Unit = ???
  def test2[F[_]: Async2]: Unit = ???

}

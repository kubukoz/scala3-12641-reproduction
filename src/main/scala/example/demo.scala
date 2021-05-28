package example

import cats.effect.kernel.Async

object demo {

  def test1[F[_]: Async]: Unit = ???
  def test2[F[_]: Async]: Unit = ???

}

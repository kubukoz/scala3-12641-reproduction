package cats.effect.kernel

import cats.MonadError
import cats.data.WriterT

trait Async2[F[_]] {}

object Async2 {
  def apply[F[_]](implicit F: Async2[F]): F.type = F

  trait WriterTAsync[F[_], X]
      extends Async2[WriterT[F, X, *]]
      with WriterTSync[F, X] {

    override protected final def delegate = super.delegate
  }

  trait WriterTSync[F[_], S] extends MonadCancel2.WriterTMonadCancel[F, S]

}

trait MonadCancel2[F[_], E]
object MonadCancel2 {

  trait WriterTMonadCancel[F[_], L]
      extends MonadCancel2[WriterT[F, L, *], Unit] {

    protected def delegate: MonadError[WriterT[F, L, *], Unit] =
      ???

  }
}

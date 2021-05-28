package cats.effect.kernel

import cats.implicits._
import cats.data.{EitherT, Ior, IorT, Kleisli, OptionT, WriterT}
import cats.{~>, Monoid, Semigroup}

import cats.arrow.FunctionK
import java.util.concurrent.atomic.AtomicReference
import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}
import cats.MonadError

trait Async2[F[_]] {}

object Async2 {
  def apply[F[_]](implicit F: Async2[F]): F.type = F

  private[effect] trait WriterTAsync[F[_], X]
      extends Async2[WriterT[F, X, *]]
      with WriterTSync[F, X] {

    override protected final def delegate = super.delegate
  }

  private[effect] trait WriterTSync[F[_], S]
      extends MonadCancel2.WriterTMonadCancel[F, S, Throwable] {
    implicit protected def F: MonadCancelThrow[F]
  }

}

object MonadCancel2 {

  private[kernel] trait WriterTMonadCancel[F[_], L, E]
      extends MonadCancel[WriterT[F, L, *], E] {

    protected def delegate: MonadError[WriterT[F, L, *], E] =
      ???

    def uncancelable[A](
        body: Poll[WriterT[F, L, *]] => WriterT[F, L, A]
    ): WriterT[F, L, A] =
      ???

    def canceled: WriterT[F, L, Unit] = ???

    def onCancel[A](
        fa: WriterT[F, L, A],
        fin: WriterT[F, L, Unit]
    ): WriterT[F, L, A] = ???

    def forceR[A, B](fa: WriterT[F, L, A])(
        fb: WriterT[F, L, B]
    ): WriterT[F, L, B] =
      ???
  }
}

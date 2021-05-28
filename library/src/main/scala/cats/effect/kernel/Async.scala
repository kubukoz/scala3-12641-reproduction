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

  trait WriterTAsync[F[_], X]
      extends Async2[WriterT[F, X, *]]
      with WriterTSync[F, X] {

    override protected final def delegate = super.delegate
  }

  trait WriterTSync[F[_], S]
      extends MonadCancel2.WriterTMonadCancel[F, S, Throwable]

}

object MonadCancel2 {

  trait WriterTMonadCancel[F[_], L, E]
      extends MonadCancel[WriterT[F, L, *], E] {

    protected def delegate: MonadError[WriterT[F, L, *], E] =
      ???

  }
}

package cats.effect.kernel

import cats.implicits._
import cats.data.{EitherT, Ior, IorT, Kleisli, OptionT, WriterT}
import cats.{~>, Monoid, Semigroup}

import cats.arrow.FunctionK
import java.util.concurrent.atomic.AtomicReference
import scala.annotation.tailrec
import scala.concurrent.{ExecutionContext, Future}

trait Async2[F[_]] {}

object Async2 {
  def apply[F[_]](implicit F: Async2[F]): F.type = F

  private[effect] trait WriterTAsync[F[_], X]
      extends Async2[WriterT[F, X, *]]
      with WriterTSync[F, X] {

    implicit protected def F: Sync[F] with GenTemporal[F, Throwable] = ???

    override protected final def delegate = super.delegate
  }

  private[effect] trait WriterTSync[F[_], S]
      extends MonadCancel.WriterTMonadCancel[F, S, Throwable] {
    implicit protected def F: MonadCancelThrow[F]
  }

}

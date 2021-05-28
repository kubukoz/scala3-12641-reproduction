package cats.effect.kernel

trait Async2[F[_]]

object Async2 {
  def apply[F[_]](implicit F: Async2[F]): F.type = F

  trait WriterTAsync[F[_], X]
      extends Async2[WriterT[F, X, *]]
      with WriterTSync[F, X] {

    override protected final def delegate = super.delegate
  }

  trait WriterTSync[F[_], S] extends MonadCancel2.WriterTMonadCancel[F, S]

}

final case class WriterT[F[_], L, V](run: F[(L, V)])

trait MonadError2[F[_], E]
trait MonadCancel2[F[_], E]

object MonadCancel2 {

  trait WriterTMonadCancel[F[_], L]
      extends MonadCancel2[WriterT[F, L, *], Unit] {

    protected def delegate: MonadError2[WriterT[F, L, *], Unit] =
      ???

  }
}

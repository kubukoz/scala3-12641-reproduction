package cats.effect.kernel

trait Async2[F[_]]

object Async2 {
  trait WriterTAsync[F[_], L]
      extends Async2[({ type LL[A] = WriterT[F, L, A] })#LL]
      with MonadCancel2.WriterTMonadCancel[F, L] {

    override def delegate = super.delegate
  }

}

case class WriterT[F[_], L, V]()

trait MonadError2[F[_]]
trait MonadCancel2[F[_]]

object MonadCancel2 {

  trait WriterTMonadCancel[F[_], L]
      extends MonadCancel2[({ type LL[A] = WriterT[F, L, A] })#LL] {

    def delegate: MonadError2[({ type LL[A] = WriterT[F, L, A] })#LL] =
      ???

  }
}

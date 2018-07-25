package utils


object CollectionImplicits {

  implicit class RichList[+A](val underlying: List[A]) extends AnyVal {
    def :?:[B >: A](value: Option[B]): List[B] = {
      value.map(_ :: underlying).getOrElse(underlying)
    }
  }

}

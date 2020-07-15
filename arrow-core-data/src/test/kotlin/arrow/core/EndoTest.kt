package arrow.core

import arrow.core.extensions.endo.monoid.monoid
import arrow.core.test.UnitSpec
import arrow.core.test.generators.endo
import arrow.core.test.laws.MonoidLaws
import arrow.typeclasses.Eq
import io.kotest.property.Arb
import io.kotest.property.arbitrary.int

class EndoTest : UnitSpec() {
  val EQ: Eq<Endo<Int>> = Eq { a, b ->
    a.f(1) == b.f(1)
  }

  init {
    testLaws(
      MonoidLaws.laws(Endo.monoid(), Arb.endo(Arb.int()), EQ)
    )
  }
}

package rational

import com.zhelenskiy.infinite.numbers.rational.*
import com.zhelenskiy.infinite.numbers.rational.invoke
import kotlin.test.Test
import kotlin.test.assertEquals

class IntegerEdgeCasesTest {
    private fun int(s: Int) = Integer(s)

    @Test
    fun euclideanDivisionLawVariousSigns() {
        val pairs = (-10..10).flatMap { a -> (-10..10).mapNotNull { b -> if (b == 0) null else a to b } }

        for ((a, b) in pairs) {
            val bigA = int(a)
            val bigB = int(b)

            val q1 = a.floorDiv(b)
            val r1 = a.mod(b)

            val bigQ1 = bigA modQuotient bigB
            val bigR1 = bigA mod bigB

            assertEquals(r1.toString(), bigR1.toString(), "$a % $b = $r1, but $bigA % $bigB = $bigR1")
            assertEquals(q1.toString(), bigQ1.toString(), "$a / $b = $q1, but $bigA / $bigB = $bigQ1")


            val q2 = a / b
            val r2 = a % b

            val bigQ2 = bigA remQuotient bigB
            val bigR2 = bigA % bigB

            assertEquals(r2.toString(), bigR2.toString(), "$a % $b = $r2, but $bigA % $bigB = $bigR2")
            assertEquals(q2.toString(), bigQ2.toString(), "$a / $b = $q2, but $bigA / $bigB = $bigQ2")
        }
    }
}

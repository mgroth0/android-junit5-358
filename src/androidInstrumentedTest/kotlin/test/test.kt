package test

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

const val CAUSE_BUG = false

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Tests {

    @ParameterizedTest
    @MethodSource("params")
    fun aTest(
        double: SomethingWithALongString,
    ) {
    }


    companion object {
        @JvmStatic
        private fun params(): List<Arguments> =
            buildList {
                add(Arguments.of(SomethingWithALongString()))
            }
    }

}

class SomethingWithALongString {
    override fun toString() = buildString {
        repeat(if (CAUSE_BUG) 200 else 150) {
            append('a')
        }
    }
}
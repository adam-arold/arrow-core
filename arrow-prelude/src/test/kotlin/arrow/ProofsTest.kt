package arrow

import arrow.meta.plugin.testing.CompilerPlugin
import arrow.meta.plugin.testing.CompilerTest
import arrow.meta.plugin.testing.Dependency
import arrow.meta.plugin.testing.assertThis
import org.junit.jupiter.api.Test

class ProofsTest {

  @Test
  fun `extension function`() {
    val arrowMetaVersion = System.getProperty("ARROW_META_VERSION")
    val arrowMetaCompilerPlugin = CompilerPlugin("Arrow Meta", listOf(Dependency("compiler-plugin:$arrowMetaVersion")))
    val prelude = Dependency("arrow-prelude")


    assertThis(CompilerTest(
      config = {
        addCompilerPlugins(arrowMetaCompilerPlugin) + CompilerTest.addDependencies(prelude)
      },
      code = {
        ProofsTestCode.semigroupExtensionCode.source
      },
      assert = {
        allOf("result".source.evalsTo("one-two"))
      }
    ))
  }

  @Test
  fun `coercion function`() {
    val arrowMetaVersion = System.getProperty("ARROW_META_VERSION")
    val arrowMetaCompilerPlugin = CompilerPlugin("Arrow Meta", listOf(Dependency("compiler-plugin:$arrowMetaVersion")))
    val prelude = Dependency("arrow-prelude")

    assertThis(CompilerTest(
      config = {
        addCompilerPlugins(arrowMetaCompilerPlugin) + addDependencies(prelude) + addArguments("-Xallow-jvm-ir-dependencies")
      },
      code = {
        ProofsTestCode.userRepositoryCode.source
      },
      assert = {
        compiles
      }
    ))
  }
}

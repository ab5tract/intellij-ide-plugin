import edument.rakuidea.build.BuildScope
import edument.rakuidea.build.complete.RakuCompletePluginBuilder
import kotlinx.coroutines.future.future
import kotlinx.coroutines.runBlocking
import org.jetbrains.intellij.build.IdeaProjectLoaderUtil
import org.jetbrains.intellij.build.TestingOptions
import org.jetbrains.intellij.build.TestingTasks
import org.jetbrains.intellij.build.impl.CompilationContextImpl
import org.jetbrains.intellij.build.impl.createCompilationContext
import org.jetbrains.intellij.build.impl.createCompilationContextBlocking

/**
 * Compiles the sources and runs tests from 'community' project. Look at [org.jetbrains.intellij.build.TestingOptions] to see which
 * options are supported.
 *
 * If you want to run this script from IntelliJ IDEA, it's important to add 'Build Project' step in 'Before Launch' section of the created
 * run configuration to ensure that required files are compiled before the script starts. It also makes sense to have
 * [org.jetbrains.intellij.build.BuildOptions.USE_COMPILED_CLASSES_PROPERTY] '-Dintellij.build.use.compiled.classes=true' in 'VM Options'
 * to skip compilation and use the compiled classes from the project output.
 */
object CommaCompleteRunTestsBuildTarget {

  private val buildScope = BuildScope()

  @JvmStatic
  fun main(args: Array<String>) {
    val communityHome = IdeaProjectLoaderUtil.guessCommunityHome(CommaCompleteRunTestsBuildTarget::class.java).communityRoot.toString()
    val future = buildScope.future {
      RakuCompletePluginBuilder(communityHome).test()
    }
    future.join()
    buildScope.clear()
  }
}

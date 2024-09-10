import edument.rakuidea.build.BuildScope
import edument.rakuidea.build.complete.RakuCompletePluginBuilder
import kotlinx.coroutines.future.future
import kotlinx.coroutines.runBlocking
import org.jetbrains.intellij.build.IdeaProjectLoaderUtil

object CommaCompletePluginBuildTarget {

  private val buildScope = BuildScope()

  @JvmStatic
  fun main(args: Array<String>) {
    val communityHome = IdeaProjectLoaderUtil.guessCommunityHome(CommaCompletePluginBuildTarget::class.java).communityRoot.toString()
    val future = buildScope.future {
      RakuCompletePluginBuilder(communityHome).build()
    }
    future.join()
    buildScope.clear()
  }
}

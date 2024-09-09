import edument.rakuidea.build.complete.RakuCompletePluginBuilder
import org.jetbrains.intellij.build.IdeaProjectLoaderUtil

object CommaCompletePluginBuildTarget {
  @JvmStatic
  suspend fun main(args: Array<String>) {
    val communityHome = IdeaProjectLoaderUtil.guessCommunityHome(javaClass).communityRoot.toString()
    RakuCompletePluginBuilder(communityHome).build()
  }
}

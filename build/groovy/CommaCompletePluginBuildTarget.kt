import edument.rakuidea.build.complete.Perl6CompletePluginBuilder
import org.jetbrains.intellij.build.IdeaProjectLoaderUtil

object CommaCompletePluginBuildTarget {
  @JvmStatic
  fun main(args: Array<String>) {
    val communityHome = IdeaProjectLoaderUtil.guessCommunityHome(javaClass).communityRoot.toString()
    Perl6CompletePluginBuilder(communityHome).build()
  }
}

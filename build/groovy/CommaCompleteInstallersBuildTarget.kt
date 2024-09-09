import edument.rakuidea.build.complete.CommaCompleteProperties
import edument.rakuidea.build.complete.RakuCompletePluginBuilder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.jetbrains.intellij.build.*
import org.jetbrains.intellij.build.impl.BuildContextImpl

object CommaCompleteInstallersBuildTarget {
  //private fun getMacHost(): MacHostProperties? {
  //  if (System.getenv("COMMA_DMG_HOST") != null) {
  //    val host = System.getenv("COMMA_DMG_HOST")
  //    val username = System.getenv("COMMA_DMG_USERNAME")
  //    val password = System.getenv("COMMA_DMG_PASSWORD")
  //    val signid = System.getenv("COMMA_DMG_SIGNID")
  //    return MacHostProperties(host, username, password, signid)
  //  }
  //  else {
  //    return null
  //  }
  //}

  @JvmStatic
  fun main(args: Array<String>) {
    val communityHome = IdeaProjectLoaderUtil.guessCommunityHome(javaClass).communityRoot.toString()
    RakuCompletePluginBuilder(communityHome).buildDistributions()
  }
}

import edument.rakuidea.build.BuildScope
import edument.rakuidea.build.complete.RakuCompletePluginBuilder
import kotlinx.coroutines.*
import kotlinx.coroutines.future.future
import org.jetbrains.intellij.build.*


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
  //val customScope = CoroutineScope(Dispatchers.Default)
  private val customScope = BuildScope()

  @OptIn(DelicateCoroutinesApi::class)
  @JvmStatic
  fun main(args: Array<String>) {
    val communityHome = IdeaProjectLoaderUtil.guessCommunityHome(CommaCompleteInstallersBuildTarget::class.java).communityRoot.toString()
    val future = customScope.future {
      RakuCompletePluginBuilder(communityHome).buildDistributions()
    }
    future.join()
    customScope.clear()
  }
}
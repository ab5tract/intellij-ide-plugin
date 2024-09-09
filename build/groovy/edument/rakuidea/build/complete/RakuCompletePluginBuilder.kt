// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package edument.rakuidea.build.complete

import org.jetbrains.intellij.build.BuildContext
import org.jetbrains.intellij.build.BuildOptions
import org.jetbrains.intellij.build.BuildTasks
import org.jetbrains.intellij.build.createBuildTasks
import org.jetbrains.intellij.build.dependencies.BuildDependenciesCommunityRoot
import org.jetbrains.intellij.build.impl.BuildContextImpl
import java.nio.file.Path

class RakuCompletePluginBuilder(
  val home : String
) {
  var homePath : Path = Path.of(home)

  suspend fun build() {
    var pluginBuildNumber : String = System.getProperty("build.number", "232.10335.12")
    var pluginsForIdeaCommunity : List<String> = listOf("edument.raku.comma.complete")
    var options : BuildOptions = BuildOptions(isInDevelopmentMode = true,
                                              //targetOs = listOf(OsFamily.currentOs),
                                              outRootDir = homePath.resolve("out/commaCP"),
                                              //buildNumber = pluginBuildNumber,
                                              incrementalCompilation = true)
    var communityRoot = BuildDependenciesCommunityRoot(homePath)
    var buildContext : BuildContext = BuildContextImpl.createContext(
                                        projectHome = homePath,
                                        productProperties = CommaCompleteProperties(communityRoot, home),
                                        options = options)

    var buildTasks : BuildTasks = createBuildTasks(buildContext)
    buildTasks.buildNonBundledPlugins(pluginsForIdeaCommunity)
  }
}
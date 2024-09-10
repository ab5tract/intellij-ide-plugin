// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package edument.rakuidea.build.complete

import kotlinx.coroutines.runBlocking
import org.jetbrains.intellij.build.*
import org.jetbrains.intellij.build.dependencies.BuildDependenciesCommunityRoot
import org.jetbrains.intellij.build.impl.BuildContextImpl
import java.nio.file.Path

class RakuCompletePluginBuilder(home : String) {
  private val homePath : Path = Path.of(home)
  private val communityRoot = BuildDependenciesCommunityRoot(homePath)
  private val pluginsForIdeaCommunity : List<String> = listOf("edument.raku.comma.complete")
  private val productProperties : CommaCompleteProperties = CommaCompleteProperties(communityRoot)

  private val options : BuildOptions = BuildOptions(isInDevelopmentMode = true,
                                                    outRootDir = homePath.resolve("out/comma"))

  suspend fun build() {
    val buildContext : BuildContext = BuildContextImpl.createContext(
                                        projectHome = homePath,
                                        productProperties = productProperties,
                                        options = options)
    createBuildTasks(buildContext).buildNonBundledPlugins(pluginsForIdeaCommunity)
  }

  suspend fun buildDistributions() {
    val buildContext : BuildContext = BuildContextImpl.createContext(
                                        projectHome = homePath,
                                        productProperties = productProperties,
                                        options = options, )
    //createBuildTasks(buildContext).buildUnpackedDistribution(includeBinAndRuntime = true, targetDirectory = homePath.resolve("out/dist"))
    createBuildTasks(buildContext).buildDistributions()
  }

  suspend fun test() {
    val context = org.jetbrains.intellij.build.impl.createCompilationContext(
      projectHome = homePath,
      defaultOutputRoot = communityRoot.communityRoot.resolve("out/tests")
    )

    val options = TestingOptions()
    options.testGroups = "COMMA_COMPLETE_TESTS"
    options.platformPrefix = "CommaCore"
    options.mainModule = "edument.raku.comma.complete"

    TestingTasks.create(context, options).runTests()
  }
}
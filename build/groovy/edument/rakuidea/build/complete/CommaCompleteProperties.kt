// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package edument.rakuidea.build.complete

import edument.rakuidea.build.CommaLinuxDistributionCustomizer
import edument.rakuidea.build.CommaMacDistributionCustomizer
import edument.rakuidea.build.CommaWindowsDistributionCustomizer
import org.jetbrains.intellij.build.*
import org.jetbrains.intellij.build.dependencies.BuildDependenciesCommunityRoot
import kotlinx.collections.immutable.*
import org.jetbrains.intellij.build.impl.PluginLayout
import java.nio.file.Files
import java.nio.file.Path

class CommaCompleteProperties(
  communityHome : BuildDependenciesCommunityRoot
) : ProductProperties() {

  override val baseFileName: String = "comma"
  init {
    reassignAltClickToMultipleCarets = true
    buildCrossPlatformDistribution = true

    brandingResourcePaths = listOf(communityHome.communityRoot.resolve("comma-build/complete/resources"))

    productLayout.productApiModules = listOf("intellij.xml.dom", "edument.raku.comma.complete")
    productLayout.productImplementationModules = listOf(
      "intellij.xml.dom.impl",
      "intellij.platform.main",
      "edument.raku.plugin"
    )
    productLayout.bundledPluginModules = mutableListOf("edument.raku.comma.complete")
    productLayout.bundledPluginModules.addAll(Files.readAllLines(communityHome.communityRoot.resolve("comma-build/build/plugin-list.txt")))
    productLayout.pluginModulesToPublish = persistentSetOf("edument.raku.comma.complete")

    productLayout.pluginLayouts = persistentListOf(PluginLayout.plugin("edument.raku.comma.complete"))

    platformPrefix = "CommaCore"
    applicationInfoModule = "edument.raku.comma.complete"
  }

  // General setup
  override fun getSystemSelector(appInfo: ApplicationInfoProperties, buildNumber: String): String {
    return "Comma${appInfo.majorVersion}.${appInfo.minorVersionMainPart}"
  }

  override fun getBaseArtifactName(appInfo: ApplicationInfoProperties, buildNumber: String): String {
    return "comma-$buildNumber"
  }

  override fun getOutputDirectoryName(appInfo: ApplicationInfoProperties): String {
    return "comma"
  }

  override fun getEnvironmentVariableBaseName(appInfo: ApplicationInfoProperties): String {
    return "COMMA"
  }

  // OS-specific customizers
  override fun createWindowsCustomizer(projectHome: String): WindowsDistributionCustomizer? {
    return CommaWindowsDistributionCustomizer(projectHome)
  }

  override fun createLinuxCustomizer(projectHome: String): LinuxDistributionCustomizer? {
    return CommaLinuxDistributionCustomizer(projectHome)
  }

  override fun createMacCustomizer(projectHome: String): MacDistributionCustomizer? {
    return CommaMacDistributionCustomizer(projectHome)
  }

  //TODO: Check the license and notice texts and adjust for community management
  override suspend fun copyAdditionalFiles(context: BuildContext, targetDir: Path) {
    super.copyAdditionalFiles(context, targetDir)
    FileSet(context.paths.communityHomeDir)
      .include("LICENSE.txt")
      .include("NOTICE.txt")
      .copyToDir(targetDir.resolve("license"))
  }
}
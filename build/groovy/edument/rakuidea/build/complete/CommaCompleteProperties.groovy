// Copyright 2000-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
package edument.rakuidea.build.complete

import org.jetbrains.intellij.build.*
import edument.rakuidea.build.CommaPropertiesBase
import edument.rakuidea.build.CommaMacDistributionCustomizer
import edument.rakuidea.build.CommaWindowsDistributionCustomizer
import org.jetbrains.intellij.build.dependencies.BuildDependenciesCommunityRoot

import java.nio.file.Files
import java.nio.file.Path

import static org.jetbrains.intellij.build.impl.PluginLayoutGroovy.plugin

/**
 * @author nik
 */
class CommaCompleteProperties extends CommaPropertiesBase {
  CommaCompleteProperties(BuildDependenciesCommunityRoot communityHome) {
    productLayout.mainJarName = "comma.jar"
    platformPrefix = "CommaCore"
    applicationInfoModule = "edument.raku.comma.complete"
    brandingResourcePaths = List.of(communityHome.communityRoot.resolve("comma-build/complete/resources"))

    productLayout.mainModules = List.of("edument.raku.comma.complete")
    productLayout.productApiModules = ["intellij.xml.dom", "edument.raku.comma.complete"]
    productLayout.productImplementationModules = [
      "intellij.xml.dom.impl",
      "intellij.platform.main",
      "edument.raku.plugin"
    ]
    productLayout.bundledPluginModules.add("edument.raku.comma.complete")
    productLayout.bundledPluginModules.addAll(Files.readAllLines(communityHome.communityRoot.resolve("comma-build/build/plugin-list.txt")))

    productLayout.pluginLayouts = CommunityRepositoryModules.COMMUNITY_REPOSITORY_PLUGINS.add(
      plugin("edument.raku.comma.complete") {
        directoryName = "comma"
        mainJarName = "comma.jar"
        withModule("edument.raku.plugin")
      })
    productLayout.pluginModulesToPublish = List.of("edument.raku.comma.complete")
  }

  @Override
  void copyAdditionalFilesBlocking(BuildContext context, String targetDirectory) {
    super.copyAdditionalFilesBlocking(context, targetDirectory)
    new FileSet(context.paths.communityHomeDir.communityRoot)
    .include("LICENSE.txt")
    .include("NOTICE.txt")
      .copyToDir(Path.of(targetDirectory, "license"))
  }

  String getSystemSelector(ApplicationInfoProperties applicationInfo, String buildNumber) {
    "CommaCP${applicationInfo.majorVersion}.${applicationInfo.minorVersionMainPart}"
  }

  @Override
  String getBaseFileName() {
    return "comma"
  }

  @Override
  String getBaseArtifactName(ApplicationInfoProperties applicationInfo, String buildNumber) {
    "commaCP-$buildNumber"
  }

  @Override
  WindowsDistributionCustomizer createWindowsCustomizer(String projectHome) {
    return new CommaWindowsDistributionCustomizer() {
      {
        installerImagesPath = "$projectHome/comma-build/build/resources"
        fileAssociations = [".p6", ".pm6", ".pl6", ".pod6", ".raku", ".rakumod", ".rakutest", ".rakudoc"]
      }

      @Override
      String getFullNameIncludingEdition(ApplicationInfoProperties applicationInfo) {
        "Comma Complete Edition"
      }
    }
  }

  @Override
  LinuxDistributionCustomizer createLinuxCustomizer(String projectHome) {
    return new LinuxDistributionCustomizer() {
      {
        iconPngPath = "$projectHome/comma-build/complete/resources/CommaCore128.png"
        snapName = "comma-complete"
        snapDescription =
          "Complete edition of the Comma Raku Integrated Development Environment."
          "Developed by Edument."
      }

      @Override
      String getRootDirectoryName(ApplicationInfoProperties applicationInfo, String buildNumber) {
        "comma-complete-${applicationInfo.isEAP() ? buildNumber : applicationInfo.fullVersion}"
      }
    }
  }

  @Override
  MacDistributionCustomizer createMacCustomizer(String projectHome) {
    return new CommaMacDistributionCustomizer() {
      {
        icnsPath = "$projectHome/comma-build/complete/resources/CommaCore.icns"
        bundleIdentifier = "com.edument.comma"
        dmgImagePath = "$projectHome/comma-build/build/dmg_background.tiff"
      }

      @Override
      String getRootDirectoryName(ApplicationInfoProperties applicationInfo, String buildNumber) {
        String suffix = applicationInfo.isEAP() ? " ${applicationInfo.majorVersion}.${applicationInfo.minorVersion} EAP" : ""
        "Comma CP${suffix}.app"
      }
    }
  }

  @Override
  String getOutputDirectoryName(ApplicationInfoProperties applicationInfo) {
    "comma"
  }
}

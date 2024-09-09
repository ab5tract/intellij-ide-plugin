// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package edument.rakuidea.build

import org.jetbrains.intellij.build.ApplicationInfoProperties
import org.jetbrains.intellij.build.BuildContext
import org.jetbrains.intellij.build.JvmArchitecture
import org.jetbrains.intellij.build.MacDistributionCustomizer
import java.nio.file.Path

class CommaMacDistributionCustomizer(projectHome : String) : MacDistributionCustomizer() {

  init {
    icnsPath = "$projectHome/comma-build/complete/resources/CommaCore.icns"
    bundleIdentifier = "com.edument.comma"
    dmgImagePath = "$projectHome/comma-build/build/dmg_background.tiff"
  }

  //TODO: no-op, apparently? verify
  //override suspend fun copyAdditionalFiles(context: BuildContext, targetDir: Path, arch: JvmArchitecture) {}

  override fun getCustomIdeaProperties(appInfo: ApplicationInfoProperties): Map<String, String> {
    return mapOf(Pair("ide.mac.useNativeClipboard", "false"))
  }

  override fun getRootDirectoryName(appInfo: ApplicationInfoProperties, buildNumber: String): String {
    val suffix = if (appInfo.isEAP) " \${applicationInfo.majorVersion}.\${applicationInfo.minorVersion} EAP" else ""
    return "Comma ${suffix}.app"
  }
}
// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package edument.rakuidea.build

import org.jetbrains.intellij.build.ApplicationInfoProperties
import org.jetbrains.intellij.build.LinuxDistributionCustomizer

class CommaLinuxDistributionCustomizer(projectHome : String) : LinuxDistributionCustomizer() {

  init {
    iconPngPath = "$projectHome/comma-build/complete/resources/CommaCore512.png"
    snapName = "comma"
    snapDescription =
      "The Comma Raku Integrated Development Environment.\nDeveloped by Edument and extended with love by the community."
  }

  override fun getRootDirectoryName(appInfo: ApplicationInfoProperties, buildNumber: String): String {
    return "comma-" + if (appInfo.isEAP) buildNumber else appInfo.fullVersion
  }
}
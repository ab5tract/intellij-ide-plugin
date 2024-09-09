// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package edument.rakuidea.build

import org.jetbrains.intellij.build.ApplicationInfoProperties
import org.jetbrains.intellij.build.BuildContext
import org.jetbrains.intellij.build.JvmArchitecture
import org.jetbrains.intellij.build.WindowsDistributionCustomizer
import java.nio.file.Path

class CommaWindowsDistributionCustomizer(projectHome : String) : WindowsDistributionCustomizer() {

  init {
    icoPath = "$projectHome/comma-build/complete/resources/CommaCore.ico"
    installerImagesPath = "$projectHome/comma-build/build/resources"
    fileAssociations = listOf(".p6", ".pm6", ".pl6", ".pod6", ".raku", ".rakumod", ".rakutest", ".rakudoc")
  }

  // TODO: Double check if necessary
  //override suspend fun copyAdditionalFiles(context: BuildContext, targetDir: Path, arch: JvmArchitecture) {}

  override fun getFullNameIncludingEdition(appInfo: ApplicationInfoProperties): String {
    return "Comma"
  }
}
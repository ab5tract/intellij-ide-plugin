// Copyright 2000-2024 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license.
package edument.rakuidea.build

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlin.coroutines.CoroutineContext

class BuildScope : CoroutineScope {
  private val job = Job()
  override val coroutineContext: CoroutineContext
    get() = Dispatchers.Default + job

  fun clear() { job.cancel() }
}
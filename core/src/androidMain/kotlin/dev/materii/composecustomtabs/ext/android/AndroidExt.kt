package dev.materii.composecustomtabs.ext.android

import android.net.Uri
import dev.materii.composecustomtabs.state.CustomTabState

val CustomTabState.uri: Uri
    get() = if(url != null) Uri.parse(url) else Uri.EMPTY

fun CustomTabState.navigate(uri: Uri) = navigate(uri.toString())
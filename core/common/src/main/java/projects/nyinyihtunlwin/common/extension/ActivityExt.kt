package projects.nyinyihtunlwin.common.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
fun Context.openUrl(
    url: String
) {
    Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }.also(::startActivity)
}
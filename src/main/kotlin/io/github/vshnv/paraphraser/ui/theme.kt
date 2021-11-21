import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font


/*private val lightPalate = lightColors(
    primary = Color(0xFF184E77),
    primaryVariant = Color(0xFF1E6091),
    secondary = Color(0xFFD9ED92),
    secondaryVariant = Color(0xFFB5E48C),
    background = Color(0xFF52B69A),
    surface = Color(0xFF168AAD),
    error = Color(0xFF99D98C),
    onPrimary = Color(0xFFFDFDFF),
    onSecondary = Color(0xFF040922),
    onBackground = Color(0xFFFDFDFF),
    onError = Color(0xFFFDFDFF),
    onSurface = Color(0xFFFDFDFF)
)*/

private val lightPalate = lightColors(
    primary = Color(0xFF184E77),
    primaryVariant = Color(0xFF1E6091),
    secondary = Color(0xFF202831),
    secondaryVariant = Color(0xFF182029),
    background = Color(0xFF52B69A),
    surface = Color(0xFF168AAD),
    error = Color(0xFF99D98C),
    onPrimary = Color(0xFFFDFDFF),
    onSecondary = Color(0xFFFFFFFF),
    onBackground = Color(0xFFFDFDFF),
    onError = Color(0xFFFDFDFF),
    onSurface = Color(0xFFFDFDFF)
)

val firaCode = FontFamily(
    Font(
        resource = "FiraCode-Bold.ttf",
        weight = FontWeight.W700,
        style = FontStyle.Normal
    ),
    Font(
        resource = "FiraCode-Light.ttf",
        weight = FontWeight.W100,
        style = FontStyle.Normal
    ),
    Font(
        resource = "FiraCode-Medium.ttf",
        weight = FontWeight.W500,
        style = FontStyle.Normal
    ),
    Font(
        resource = "FiraCode-Regular.ttf",
        weight = FontWeight.W300,
        style = FontStyle.Normal
    ),
    Font(
        resource = "FiraCode-Retina.ttf",
        weight = FontWeight.W600,
        style = FontStyle.Normal
    ),
    Font(
        resource = "FiraCode-SemiBold.ttf",
        weight = FontWeight.W400,
        style = FontStyle.Normal
    )
)

val firaCodeTypography = Typography(defaultFontFamily = firaCode)

@Composable
fun ApplicationTheme(content: @Composable () -> Unit) {
    MaterialTheme(colors = lightPalate) {
        content()
    }
}
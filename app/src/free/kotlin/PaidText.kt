import androidx.compose.foundation.background
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun PaidText(isPaidVersion: Boolean, modifier: Modifier = Modifier) {
    if (isPaidVersion) {
        Text(text = "Paid Text")
    } else {
        Text(text = "Free Text", modifier = modifier.background(Color.Red))
    }
}
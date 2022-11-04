package ca.tetervak.lemonadelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ca.tetervak.lemonadelab.ui.theme.LemonadeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeLabTheme {
                LemonadeApp()
            }
        }
    }
}

val textLabelResourceIds = intArrayOf(
    R.string.tap_the_lemon_tree, R.string.keep_tapping_the_lemon,
    R.string.tap_the_lemonade, R.string.tap_the_empty_glass
)
val drawableResourceIds = intArrayOf(
    R.drawable.lemon_tree, R.drawable.lemon,
    R.drawable.glass_of_lemonade, R.drawable.empty_glass
)
val contentDescriptionResourceIds = intArrayOf(
    R.string.lemon_tree, R.string.lemon,
    R.string.glass_of_lemonade, R.string.empty_glass
)

@Composable
fun LemonadeApp() {

    // Current step the app is displaying (remember allows the state to be retained
    // across recompositions).
    var currentPageIndex by remember { mutableStateOf(0) }

    // Number of times the lemon needs to be squeezed to turn into a glass of lemonade
    var squeezeCount by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        LemonadeCard(pageIndex = currentPageIndex, onImageClick = { /*TODO*/
            when(currentPageIndex){
                0 -> {
                    currentPageIndex = 1
                    squeezeCount = (2..4).random()
                }
                1 -> {
                    squeezeCount--
                    if(squeezeCount == 0){
                        currentPageIndex = 2
                    }
                }
                2 -> currentPageIndex = 3
                3 -> currentPageIndex = 0
            }
        })
    }
}

@Composable
fun LemonadeCard(
    pageIndex: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text(
            text = stringResource(textLabelResourceIds[pageIndex]),
            fontSize = 18.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(drawableResourceIds[pageIndex]),
            contentDescription = stringResource(contentDescriptionResourceIds[pageIndex]),
            modifier = Modifier
                .wrapContentSize()
                .clickable(
                    onClick = onImageClick
                )
                .border(
                    BorderStroke(2.dp, Color(105, 205, 216)),
                    shape = RoundedCornerShape(4.dp)
                )
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun LemonPreview() {
    LemonadeLabTheme() {
        LemonadeApp()
    }
}
package cn.yl.compse.samples

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("compseSamples","compseSamples onCreate...")
        Log.i("compseSamples","compseSamples onCreate...")
        setContent {
            Lib_permissionTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column() {
                        Greeting(Message("Android", "Say Hi "))
                        Spacer(modifier = Modifier.height(8.dp))
                        Conversation(SampleData.conversationSample)
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(msg: Message) {
    Row {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_background),
            contentDescription = "Contract profile picture",
            modifier = Modifier
                .size(40.0.dp)
                .clip(CircleShape)
                .border(0.5.dp, MaterialTheme.colorScheme.secondary, CircleShape)
                .clickable(onClick = {
                    
                })
        )

        Spacer(modifier = Modifier.width(8.dp))

        var isExpanded by remember {
            mutableStateOf(false)
        }
        val surfaceColor by animateColorAsState(
            targetValue = if (isExpanded) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.surface
        )

        Column(modifier = Modifier.clickable { isExpanded = !isExpanded }) {
            Text(
                text = "$${msg.author}",
                color = MaterialTheme.colorScheme.secondaryContainer, // secondaryVariant?
                style = MaterialTheme.typography.titleMedium,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                tonalElevation = 1.dp,
                shadowElevation = 1.dp,
                color = surfaceColor,
                modifier = Modifier
                    .animateContentSize()
                    .padding(1.dp)
            ) {
                Text(
                    text = msg.body,
                    modifier = Modifier.padding(all = 4.dp),
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = if (isExpanded) Int.MAX_VALUE else 1,
                )
            }
        }
    }

}

/**
 * 借助 @Preview 注解，可以在 Android Studio 中预览可组合函数，而无需构建应用并将其安装到 Android 设备或模拟器中。
 * 该注解必须用于不接受参数的可组合函数。因此，您无法直接预览 Greeting 函数，而是需要创建另一个名为 DefaultPreview 的函数，
 * 由该函数使用适当的参数调用 Greeting。
 *
 * 请在 @Composable 上方添加 @Preview 注解。
 *
 * Android Studio 会添加一个预览窗口，您可以点击拆分（设计/代码）视图以展开此窗口。
 * 此窗口会显示由标有 @Preview 注解的可组合函数创建的界面元素的预览。
 * 任何时候，如需更新预览，请点击预览窗口顶部的刷新按钮。
 */
@Preview(showBackground = true)
//@Preview(name = "Light Mode")
//@Preview(
//    uiMode = Configuration.UI_MODE_NIGHT_YES,
//    showBackground = true,
//    name = "Dark Mode"
//)
@Composable
fun DefaultPreview() {
    Lib_permissionTheme {
        Greeting(Message("google", "Android"))
    }
}

@Composable
fun Conversation(messages: List<Message>) {
    LazyColumn {
        items(items = messages) { message ->
            Greeting(msg = message)
        }
    }
}

/* // 可以启用这里直接看部分效果
@Preview
@Composable
fun PreviewConversation() {
    Lib_permissionTheme {
        Conversation(SampleData.conversationSample)
    }
}*/

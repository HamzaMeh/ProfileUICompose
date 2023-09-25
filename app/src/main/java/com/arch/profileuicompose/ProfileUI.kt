package com.arch.profileuicompose

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arch.profileuicompose.ui.theme.DarkGreen
import com.arch.profileuicompose.ui.theme.Typography

@Preview(showBackground = true)
@Composable
fun ProfileUi() {
    Scaffold(
        modifier = Modifier.drawBehind {
            val p1 = Offset(size.width / 2f, 1200f) // Bottom vertex
            val p2 = Offset(0f, 380f)                    // Top-left vertex
            val p3 = Offset(size.width, 380f)

            // Draw the rounded top rectangle on the canvas
            drawRoundRect(
                color = DarkGreen,
                topLeft = Offset(0f, 0f),
                size = Size(size.width, 150.dp.toPx()),
                cornerRadius = CornerRadius(x = 20f, y = 20F)
            )

            // Create a Path for the inverted triangle
            val invertedTrianglePath = Path().apply {
                moveTo(p1.x, p1.y)
                lineTo(p2.x, p2.y)
                lineTo(p3.x, p3.y)
                close()
            }

            // Draw the inverted triangle on the canvas
            drawPath(
                path = invertedTrianglePath,
                color = DarkGreen
            )
        },
        backgroundColor = Color.Transparent,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Profile",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                        color = Color.White
                    )
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.MoreVert,
                            contentDescription = "More",
                            tint = Color.White
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                backgroundColor = Color.Transparent,
                elevation = 0.dp
            )
        },

        ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(it)
                .verticalScroll(scrollState), verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Header("Mark Wood", "London, UK")
            FollowersLike()
            Friends()
            Photos()
            Spacer(modifier = Modifier.padding(16.dp))
        }

    }

}


@Composable
fun Header(name: String, location: String) {

    var text by remember { mutableStateOf("Follow") }
    var bgColor by remember {
        mutableStateOf(DarkGreen)
    }
    var textColor by remember {
        mutableStateOf(Color.White)
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 40.dp, end = 10.dp)
            .background(Color.Transparent)
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp),
            shape = RoundedCornerShape(4.dp),
            elevation = 4.dp
        ) {

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 30.dp)
            ) {

                Text(text = name, style = Typography.h1, modifier = Modifier.padding(top = 15.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location",
                        modifier = Modifier
                            .alpha(0.5f)
                            .size(20.dp)
                    )
                    Text(
                        text = location,
                        style = Typography.body2,
                        modifier = Modifier
                            .padding(start = 2.dp)
                            .alpha(0.7f),
                    )

                }

                Button(
                    onClick = {
                        text = if (text == "Follow") {
                            bgColor = Color.White
                            textColor = Color.Black
                            "Following"
                        } else {
                            bgColor = DarkGreen
                            textColor = Color.White
                            "Follow"
                        }
                    },
                    modifier = Modifier
                        .padding(vertical = 20.dp),
                    colors = ButtonDefaults.buttonColors(bgColor),
                    shape = RoundedCornerShape(30.dp),
                    border = if (bgColor == Color.White) BorderStroke(1.dp, DarkGreen) else null
                ) {
                    Text(
                        text = text,
                        style = Typography.h1,
                        color = textColor,
                        modifier = Modifier.padding(vertical = 5.dp, horizontal = 30.dp)
                    )
                }

            }
        }

        Image(
            painterResource(id = R.drawable.gerald_g),
            contentDescription = "Profile",
            modifier = Modifier
                .clip(
                    CircleShape
                )
                .size(75.dp)
                .align(Alignment.TopCenter),
        )
    }
}

@Composable
fun FollowersLike() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.padding(40.dp)
        ) {
            CountDetails(count = "224", type = "Following")
            Divider(
                modifier = Modifier
                    .height(45.dp)
                    .width(1.dp)
            )
            CountDetails(count = "48.6k", type = "Followers")
            Divider(
                modifier = Modifier
                    .height(45.dp)
                    .width(1.dp)
            )
            CountDetails(count = "3.2M", type = "Likes")
        }


    }
}

@Composable
fun Friends() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "Friends", style = Typography.h6)
                Text(
                    text = "See All",
                    style = Typography.h6,
                    color = Color.Blue,
                    modifier = Modifier.clickable { })
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                FriendBox(drawable = R.drawable.gerald_g, name = "Glenn")
                FriendBox(drawable = R.drawable.gerald_g, name = "Mark")
                FriendBox(drawable = R.drawable.gerald_g, name = "Sarah")
            }
        }
    }
}

@Composable
fun CountDetails(count: String, type: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = count, style = Typography.h6)
        Text(text = type, style = Typography.body1)
    }
}


@Composable
fun FriendBox(drawable: Int, name: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier.width(50.dp)
    ) {
        Image(
            painter = painterResource(id = drawable),
            contentDescription = "Image",
            modifier = Modifier.size(50.dp)
        )
        Text(name, style = Typography.body2)
    }
}

@Composable
fun Photos() {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(4.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 20.dp)) {
            Text(
                text = "Photos",
                style = Typography.h6,
                modifier = Modifier.padding(vertical = 20.dp)
            )
            Image(
                modifier = Modifier
                    .padding(bottom = 20.dp)
                    .clip(RoundedCornerShape(8.dp)),
                painter = painterResource(id = R.drawable.image),
                contentDescription = ""
            )
        }

    }
}
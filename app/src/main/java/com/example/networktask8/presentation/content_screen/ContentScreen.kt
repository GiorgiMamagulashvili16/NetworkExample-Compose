package com.example.networktask8.presentation.content_screen

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.example.networktask8.domain.model.Resource
import com.example.networktask8.domain.model.User
import com.example.networktask8.presentation.navigation.Screens
import com.example.networktask8.presentation.content_screen.states.ContentSortState
import org.koin.androidx.compose.getViewModel

@Composable
fun ContentScreen(
    navController: NavController
) {
    val vm: ContentViewModel = getViewModel()

    val screenState = vm.screenState.value
    val listState = rememberLazyListState()

    Scaffold(modifier = Modifier.fillMaxSize(), floatingActionButton = {
        FloatingActionButton(
            onClick = { navController.navigate(route = Screens.CreateUser.route) },
            backgroundColor = MaterialTheme.colors.primary
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            ContentSortSection(
                onButtonClickAction = {
                    vm.getContentEvent(it)
                }, sortState = screenState.contentSortState, modifier = Modifier
                    .fillMaxWidth()
                    .padding(18.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))

            when {
                screenState.userDataFetched != null -> {
                    LazyColumn(modifier = Modifier.fillMaxSize(), state = listState) {
                        items(items = screenState.userDataFetched) { user ->
                            UserItem(user = user)
                        }
                    }
                }
                screenState.singleUserFetched != null -> {
                    RandomUserSection(
                        user = screenState.singleUserFetched,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally)
                    )
                }
                screenState.resourcesFetched != null -> {
                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(items = screenState.resourcesFetched) { resource ->
                            ResourceItem(
                                resource = resource,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            )
                        }
                    }
                }
                screenState.error != null -> {
                    Text(
                        text = screenState.error,
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                screenState.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(CenterHorizontally))
                }
            }
        }
    }
}

@Composable
fun ResourceItem(
    modifier: Modifier = Modifier,
    resource: Resource
) {
    Box(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Canvas(modifier = Modifier.size(90.dp)) {
                drawRoundRect(
                    color = Color(
                        android.graphics.Color.parseColor(
                            resource.color
                        )
                    ),
                    size = size,
                    cornerRadius = CornerRadius(16.dp.toPx())
                )
            }
            Spacer(modifier = Modifier.width(20.dp))
            Text(
                text = "Name is: ${resource.name}\n year: ${resource.year}",
                style = MaterialTheme.typography.h6
            )
        }
    }
}

@Composable
fun RandomUserSection(
    modifier: Modifier = Modifier,
    user: User
) {
    Column(modifier = modifier) {
        val painter = rememberAsyncImagePainter(user.avatar)
        Image(
            painter = painter,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
                .padding(5.dp)
                .align(CenterHorizontally),
            contentScale = ContentScale.Crop
        )
        if (painter.state == AsyncImagePainter.State.Loading(painter)) {
            CircularProgressIndicator()
        }
        Spacer(modifier = Modifier.height(60.dp))
        Text(text = user.firstName, style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(10.dp))
        Text(text = user.lastName, style = MaterialTheme.typography.body2)
    }
}

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    user: User
) {
    Box(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            val painter = rememberAsyncImagePainter(user.avatar)
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(90.dp)
                    .padding(5.dp),
                contentScale = ContentScale.Crop
            )
            if (painter.state == AsyncImagePainter.State.Loading(painter)) {
                CircularProgressIndicator()
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Text(text = user.firstName, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = user.lastName, style = MaterialTheme.typography.body2)
            }
        }
    }
}

@Composable
fun ContentSortSection(
    modifier: Modifier = Modifier,
    onButtonClickAction: (state: ContentSortState) -> Unit,
    sortState: ContentSortState
) {
    Column(
        modifier = modifier
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {
            MyRadioButton(
                text = "Users",
                checked = sortState is ContentSortState.AllUser,
                onSelected = { onButtonClickAction.invoke(ContentSortState.AllUser) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            MyRadioButton(
                text = "Random",
                checked = sortState is ContentSortState.RandomUser,
                onSelected = { onButtonClickAction.invoke(ContentSortState.RandomUser) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            MyRadioButton(
                text = "Resources",
                checked = sortState is ContentSortState.AllResource,
                onSelected = { onButtonClickAction.invoke(ContentSortState.AllResource) }
            )
        }
    }
}

@Composable
fun MyRadioButton(
    text: String,
    checked: Boolean,
    onSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.clickable {
            onSelected.invoke()
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = checked,
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colors.primary,
                unselectedColor = MaterialTheme.colors.onBackground
            ),
            onClick = {}
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = text, style = MaterialTheme.typography.body1)
    }
}
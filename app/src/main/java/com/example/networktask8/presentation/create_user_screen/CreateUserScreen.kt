package com.example.networktask8.presentation.create_user_screen

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.networktask8.data.data_source.model.response.CreateUserResponse
import com.example.networktask8.presentation.create_user_screen.states.CreateUserStates
import org.koin.androidx.compose.getViewModel

@Composable
fun CreateUserScreen() {
    val vm: CreateUserViewModel = getViewModel()
    val screenState = vm.screenState.value

    Column(modifier = Modifier.fillMaxSize()) {
        UserCreateSection(vm = vm)
        Button(
            modifier = Modifier
                .fillMaxWidth(0.4f)
                .height(50.dp)
                .align(CenterHorizontally),
            onClick = {
                vm.createUserEvent(CreateUserStates.CreateUser)
            }
        ) {
            Text(text = "Create User", style = MaterialTheme.typography.body2)
        }
        when {
            screenState.userIsCreated != null -> {
                DisplayCreatedUser(
                    userResponse = screenState.userIsCreated,
                    modifier = Modifier
                        .align(CenterHorizontally)
                )
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

@Composable
fun DisplayCreatedUser(
    modifier: Modifier = Modifier,
    userResponse: CreateUserResponse
) {
    Spacer(modifier = Modifier.height(20.dp))
    Box(modifier = modifier) {
        Text(
            text = "username is: ${userResponse.name} \nuser job is: ${userResponse.job}",
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun UserCreateSection(
    vm: CreateUserViewModel
) {
    Spacer(modifier = Modifier.height(200.dp))
    TextField(
        value = vm.userName.value,
        onValueChange = { newValue ->
            vm.createUserEvent(
                CreateUserStates.EnteredUserName(
                    newValue
                )
            )
        },
        label = {
            Text(text = "Username", color = Color.White)
        },
        singleLine = true,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            backgroundColor = Color.Transparent
        )
    )
    Spacer(modifier = Modifier.height(20.dp))
    TextField(
        value = vm.job.value,
        onValueChange = { newValue ->
            vm.createUserEvent(
                CreateUserStates.EnteredUserJob(
                    newValue
                )
            )
        },
        label = {
            Text(text = "User job", color = Color.White)
        },
        singleLine = true,
        modifier = Modifier
            .height(60.dp)
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
            backgroundColor = Color.Transparent
        )
    )
    Spacer(modifier = Modifier.height(20.dp))
}
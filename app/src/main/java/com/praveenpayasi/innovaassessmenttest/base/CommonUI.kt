package com.praveenpayasi.innovaassessmenttest.base

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.praveenpayasi.innovaassessmenttest.R
import com.praveenpayasi.innovaassessmenttest.data.model.Astronomy

@Composable
fun ShowLoading() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        val contentDesc = stringResource(R.string.loading)
        CircularProgressIndicator(modifier = Modifier
            .align(Alignment.Center)
            .semantics {
                contentDescription = contentDesc
            })
    }
}

@Composable
fun ShowError(text: String, retryClicked: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_cancel),
            tint = Color.Red,
            contentDescription = null,
            modifier = Modifier
                .width(120.dp)
                .height(120.dp)
        )
        Text(
            text = stringResource(R.string.error_title),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Red,
            fontSize = 18.sp,
            modifier = Modifier
                .padding(4.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Gray,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(4.dp)
        )

        Button(
            onClick = { retryClicked() },
            shape = RoundedCornerShape(0.dp),
            modifier = Modifier
                .padding(top = 20.dp)
                .height(50.dp)
                .width(140.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            )
        ) {
            Text(text = stringResource(R.string.try_again))
        }
    }
}

@Composable
fun Astronomy(astronomy: Astronomy) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        AstronomyImage(astronomy)
        AstronomyTitle(astronomy.title)
        ExplainationText(astronomy.explanation)
    }

}

@Composable
fun AstronomyImage(astronomy: Astronomy) {
    AsyncImage(
        model = astronomy.url,
        contentDescription = astronomy.title,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth()
    )
}

@Composable
fun AstronomyTitle(title: String) {
    if (title.isNotEmpty()) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            maxLines = 2,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@Composable
fun ExplainationText(description: String?) {
    if (!description.isNullOrEmpty()) {
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            modifier = Modifier.padding(4.dp)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlobalTopBar(
    title: String,
    shouldShowBackIcon: Boolean = true,
    onBackClick: () -> Unit,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary,
        ),
        title = { Text(text = title) },
        navigationIcon = {
            if (shouldShowBackIcon)
                Icon(
                    modifier = Modifier.clickable { onBackClick() },
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "ArrowBack",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
        }
    )

}
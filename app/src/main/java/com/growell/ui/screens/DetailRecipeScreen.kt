package com.growell.ui.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.growell.R
import com.growell.ui.theme.GrowellTheme
import com.growell.ui.theme.Poppins

@Composable
fun DetailRecipeScreen() {
    var showDialog by remember { mutableStateOf(false) }
    var selectedChild by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.asset_recipe1),
            contentDescription = "Background Image",
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 200.dp)
                .aspectRatio(1.5f),
            contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 240.dp)
                .verticalScroll(rememberScrollState())
                .align(Alignment.TopStart)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .requiredHeightIn(min = 200.dp)
                    .padding(0.dp),
                backgroundColor = Color.White,
                shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = "Ide makanan pembuatan resep sandwich\n" + "sehat dan bergizi",
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.Black,
                        fontFamily = Poppins
                    )
                    Spacer(modifier = Modifier.padding(bottom = 30.dp))
                    Text(
                        "Berikut ini adalah resep sandwich sehat yang dapat kamu coba:\n" + "Bahan-bahan:\n" + "4 lembar roti gandum utuh\n" + "150 gram daging ayam rebus atau panggang, iris tipis\n" + "1 buah alpukat matang, dihaluskan\n" + "1 buah tomat, iris tipis\n" + "1/2 buah timun, iris tipis\n" + "Daun selada segar\n" + "2 sendok makan mayones rendah lemak\n" + "1 sendok teh mustard\n" + "Garam dan merica secukupnya\n" + "\n" + "Cara membuat:\n" + "Campurkan mayones dan mustard dalam sebuah wadah kecil. Aduk rata dan sisihkan.\n" + "Ambil selembar roti gandum, oleskan lapisan alpukat halus di atasnya.\n" + "Letakkan irisan daging ayam rebus atau panggang di atas lapisan alpukat.\n" + "Tambahkan irisan tomat, timun, dan daun selada di atas daging ayam.\n" + "Beri sedikit garam dan merica untuk memberi cita rasa pada sandwich.\n" + "Oleskan campuran mayones dan mustard yang sudah disiapkan di atas roti kedua.",
                        fontSize = 11.sp, color = Color(0xFF4F4F4F), fontFamily = Poppins,
                    )
                    Spacer(modifier = Modifier.padding(top = 40.dp))
                    Button(
                        onClick = { showDialog = true },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(Color(0xFF43ADA6)) // Warna latar belakang #43ADA6
                    ) {
                        Text(
                            text = "Mulai Masak",
                            color = Color.White
                        )
                    }
                }
            }
        }
        Box(
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)
        ) {
            IconButton(
                onClick = { /* Aksi ketika tombol back ditekan */ },
                modifier = Modifier.background(Color.White, shape = CircleShape)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_left_icon),
                    contentDescription = "Back",
                    tint = Color.Black
                )
            }
        }
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = {
                Text(text = "Pilih Anak")
            },
            text = {
                Column {
                    Text(
                        text = "Pilih salah satu anak:",
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    RadioButtonWithText(
                        text = "Anak 1",
                        isSelected = selectedChild == "Anak 1",
                        onSelected = { selectedChild = "Anak 1" }
                    )
                    RadioButtonWithText(
                        text = "Anak 2",
                        isSelected = selectedChild == "Anak 2",
                        onSelected = { selectedChild = "Anak 2" }
                    )
                    // Tambahkan radio button anak lainnya di sini
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = { showDialog = false },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "Batal")
                    }
                    TextButton(
                        onClick = { showDialog = false },
                        modifier = Modifier.padding(end = 8.dp)
                    ) {
                        Text(text = "Pilih")
                    }
                }
            }
        )
    }

}

@Composable
fun RadioButtonWithText(
    text: String,
    isSelected: Boolean,
    onSelected: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable { onSelected() }
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null // Untuk mencegah radio button menjadi tidak responsif saat diklik pada Row
        )
        Icon(
            painter = painterResource(id = R.drawable.star_icon),
            contentDescription = null,
            modifier = Modifier.padding(start = 8.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 8.dp)
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_5")
@Composable
fun DetailRecipeScreenPreview() {
    GrowellTheme {
        DetailRecipeScreen()
    }
}
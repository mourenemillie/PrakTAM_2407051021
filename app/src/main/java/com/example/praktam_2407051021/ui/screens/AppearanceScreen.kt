package com.example.praktam_2407051021.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

val WarmBeige = Color(0xFFE8D5C8)
val SoftRose = Color(0xFFD69BA6)
val SageGreen = Color(0xFFA1B39F)
val NightSlate = Color(0xFF4C5864)

@Composable
fun AppearanceScreen(navController: NavController) {
    var selectedColor by remember { mutableStateOf("Warm Beige") }
    var selectedFont by remember { mutableStateOf("Serif") }
    var selectedCard by remember { mutableStateOf("Elevated") }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            Row(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp, vertical = 16.dp).statusBarsPadding(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = MaterialTheme.colorScheme.onBackground)
                }
                Text(
                    text = "Appearance",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.weight(1f).padding(end = 48.dp),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        },
        bottomBar = {
            Box(modifier = Modifier.fillMaxWidth().padding(24.dp), contentAlignment = Alignment.CenterEnd) {
                Button(
                    onClick = { navController.popBackStack() },
                    colors = ButtonDefaults.buttonColors(containerColor = BrownButton),
                    shape = RoundedCornerShape(24.dp),
                    contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
                ) {
                    Text("Save Themes", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            contentPadding = PaddingValues(24.dp)
        ) {
            item {
                SectionHeader("Color Palette", "Choose the primary tone for your digital space.")
                
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    ColorOption(name = "Warm Beige", color = WarmBeige, isSelected = selectedColor == "Warm Beige", onClick = { selectedColor = "Warm Beige" }, modifier = Modifier.weight(1f))
                    ColorOption(name = "Soft Rose", color = SoftRose, isSelected = selectedColor == "Soft Rose", onClick = { selectedColor = "Soft Rose" }, modifier = Modifier.weight(1f))
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                    ColorOption(name = "Sage Green", color = SageGreen, isSelected = selectedColor == "Sage Green", onClick = { selectedColor = "Sage Green" }, modifier = Modifier.weight(1f))
                    ColorOption(name = "Night Slate", color = NightSlate, isSelected = selectedColor == "Night Slate", onClick = { selectedColor = "Night Slate" }, modifier = Modifier.weight(1f))
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                Divider(color = MaterialTheme.colorScheme.surfaceVariant)
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                SectionHeader("Typography", "Select the font style for your main headings.")
                
                FontOption(
                    title = "Serif (Playfair)",
                    sampleText = "The quick brown fox jumps over the lazy dog.",
                    fontFamily = FontFamily.Serif,
                    isSelected = selectedFont == "Serif",
                    onClick = { selectedFont = "Serif" }
                )
                Spacer(modifier = Modifier.height(16.dp))
                FontOption(
                    title = "Sans-serif (Inter)",
                    sampleText = "The quick brown fox jumps over the lazy dog.",
                    fontFamily = FontFamily.SansSerif,
                    isSelected = selectedFont == "Sans-serif",
                    onClick = { selectedFont = "Sans-serif" }
                )

                Spacer(modifier = Modifier.height(32.dp))
                Divider(color = MaterialTheme.colorScheme.surfaceVariant)
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                SectionHeader("Card Style", "How should your entries and content containers appear?")
                
                CardStyleOption(
                    title = "Elevated",
                    isSelected = selectedCard == "Elevated",
                    isOutlined = false,
                    onClick = { selectedCard = "Elevated" }
                )
                Spacer(modifier = Modifier.height(16.dp))
                CardStyleOption(
                    title = "Flat",
                    isSelected = selectedCard == "Flat",
                    isOutlined = true,
                    onClick = { selectedCard = "Flat" }
                )
                
                Spacer(modifier = Modifier.height(80.dp)) // padding for bottom bar
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, subtitle: String) {
    Text(text = title, fontSize = 20.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.Serif, color = PrimaryText, modifier = Modifier.padding(bottom = 8.dp))
    Text(text = subtitle, fontSize = 14.sp, color = SecondaryText, modifier = Modifier.padding(bottom = 24.dp))
}

@Composable
fun ColorOption(name: String, color: Color, isSelected: Boolean, onClick: () -> Unit, modifier: Modifier = Modifier) {
    val outlineModifier = if (isSelected) Modifier.border(1.dp, PrimaryText, RoundedCornerShape(12.dp)) else Modifier
    Card(
        modifier = modifier.height(120.dp).clickable { onClick() }.then(outlineModifier),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(modifier = Modifier.size(50.dp).background(color, CircleShape))
            Spacer(modifier = Modifier.height(12.dp))
            Text(text = name, fontSize = 12.sp, fontWeight = FontWeight.Medium, color = PrimaryText)
        }
    }
}

@Composable
fun FontOption(title: String, sampleText: String, fontFamily: FontFamily, isSelected: Boolean, onClick: () -> Unit) {
    val outlineModifier = if (isSelected) Modifier.border(1.dp, PrimaryText, RoundedCornerShape(12.dp)) else Modifier
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.then(outlineModifier),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(text = title, fontSize = 12.sp, fontWeight = FontWeight.Bold, color = PrimaryText, modifier = Modifier.padding(bottom = 12.dp))
            Text(text = sampleText, fontSize = 18.sp, fontFamily = fontFamily, color = PrimaryText)
        }
    }
}

@Composable
fun CardStyleOption(title: String, isSelected: Boolean, isOutlined: Boolean, onClick: () -> Unit) {
    val containerColor = Color.White
    Card(
        modifier = Modifier.fillMaxWidth().clickable { onClick() }.then(if(isSelected) Modifier.border(1.dp, PrimaryText, RoundedCornerShape(12.dp)) else Modifier),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = containerColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Preview Box
            val previewOutline = if (isOutlined) Modifier.border(1.dp, MaterialTheme.colorScheme.outline.copy(alpha = 0.5f), RoundedCornerShape(8.dp)) else Modifier
            val elevation = if (isOutlined) 0.dp else 4.dp
            
            Card(
                modifier = Modifier.fillMaxWidth().height(80.dp).padding(bottom = 16.dp).then(previewOutline),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = elevation),
                shape = RoundedCornerShape(8.dp)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(if (isOutlined) "Outlined" else "Soft Shadow", fontSize = 12.sp, color = SecondaryText)
                }
            }
            
            Row(verticalAlignment = Alignment.CenterVertically) {
                RadioButton(
                    selected = isSelected,
                    onClick = onClick,
                    colors = RadioButtonDefaults.colors(selectedColor = PrimaryText)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = title, fontSize = 14.sp, fontWeight = FontWeight.Medium, color = PrimaryText)
            }
        }
    }
}

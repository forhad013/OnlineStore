package com.greenrobotdev.onlinestore.utils

import com.arkivanov.essenty.parcelable.Parceler
import kotlinx.datetime.Instant

expect object InstantParceler: Parceler<Instant>

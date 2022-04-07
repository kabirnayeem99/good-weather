package org.asdtm.goodweather.ui.home

import org.asdtm.goodweather.common.base.UserMessage

data class HomeUiState(
    val isLoading: Boolean = false,
    val message: List<UserMessage> = emptyList()
)
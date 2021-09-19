package com.maventech.cryptoapp.ui.callbacks

import com.maventech.cryptoapp.model.dashboard.DashboardItem

interface DashboardItemClickCallback {
    fun onClick(response: DashboardItem?)

}
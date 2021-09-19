package com.matecho.wms.model

class UserInfo {
    var firstName: String? = null
    var lastName: String? = null
    var role: String? = null
    var roleId = 0
    var userID = 0
    var clientId = 0
    var userEmail: String? = null

    override fun toString(): String {
        return "UserInfo{" +
                "firstName = '" + firstName + '\'' +
                ",lastName = '" + lastName + '\'' +
                ",role = '" + role + '\'' +
                ",roleId = '" + roleId + '\'' +
                ",userEmail = '" + userEmail + '\'' +
                "}"
    }
}
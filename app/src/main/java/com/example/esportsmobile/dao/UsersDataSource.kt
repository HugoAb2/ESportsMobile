package com.example.esportsmobile.dao

import com.example.esportsmobile.R
import com.example.esportsmobile.model.User

class UsersDataSource {

    companion object{

        fun createUsersList(): ArrayList<User> {
            val list = ArrayList<User>()

            list.add(User(
                "1",
                "Hugo",
                24,
                "Brazil",
                "rodrigueshugoab2@gmail.com",
                "*****",
                R.drawable.profile))

            list.add(User(
                "2",
                "Guilherme",
                20,
                "Brazil",
                "guilherme.sogliato@gmail.com",
                "*****",
                R.drawable.profile2))

            return list
        }

    }
}
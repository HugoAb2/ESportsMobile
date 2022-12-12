package com.example.esportsmobile.dao

import com.example.esportsmobile.R
import com.example.esportsmobile.model.User

class UsersDataSource {

    companion object{

        fun createUsersList(): ArrayList<User> {
            val list = ArrayList<User>()

            list.add(User(
                "U56LDMk6U1cJ01GOzOKbaaaZiMw2",
                "hugo",
                "24",
                "Brazil",
                "hugo@gmail.com",
                "******",
                ArrayList<String>(),
                ArrayList<String>(),
                R.drawable.profile))

            list.add(User(
                "2",
                "Guilherme",
                "20",
                "Brazil",
                "guilherme@gmail.com",
                "******",
                ArrayList<String>(),
                ArrayList<String>(),
                R.drawable.profile2))

            list.add(User(
                "U7gHrfN7NLPLgvf7FI11Sk8dbvI2",
                "adm",
                "99",
                "Brazil",
                "adm@gmail.com",
                "******",
                ArrayList<String>(),
                ArrayList<String>(),
                R.drawable.ic_baseline_person_24))

            list.add(User(
                "ZSoaglYXh1dWFy5woM2wh22Xezn1",
                "Professor",
                "22",
                "Brazil",
                "prof@gmail.com",
                "******",
                ArrayList<String>(),
                ArrayList<String>(),
                R.drawable.ic_baseline_person_24))

            list.add(User(
                "iocw8FjCDpa2s6ihRACSnfqMWaq2",
                "natalia",
                "20",
                "Brazil",
                "guilherme.sogliato@gmail.com",
                "******",
                ArrayList<String>(),
                ArrayList<String>(),
                R.drawable.ic_baseline_person_24))

            return list
        }

    }
}
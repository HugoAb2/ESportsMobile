package com.example.esportsmobile.dao

import com.example.esportsmobile.model.Comment
import com.google.firebase.firestore.FirebaseFirestore

class CommentsDataSource {

    private val list = ArrayList<Comment>()

    fun getComment(commentID : String): Comment {
        var responseComment = Comment("", "", "", "")
        FirebaseFirestore.getInstance().collection("Comments").document(commentID)
            .addSnapshotListener{ comment,_ ->
                if (comment != null){
                     responseComment = Comment(commentID,
                        comment.getString("author").toString(),
                        comment.getString("target").toString(),
                        comment.getString("text").toString())
                }
        }
        return responseComment
    }

    fun createUsersList(): ArrayList<Comment> {

        list.apply {
            add(Comment("12c3bfc7-4fe4-477b-a1d7-cb28fef8ccd8",
                "Guilherme",
                "Miners",
                "veio a passeio"
            ))

            add(Comment("230294ca-9a51-4635-8761-bb04372d4cbd",
                "Guilherme",
                "Pain",
                "bastante potencial"
            ))

            add(Comment("2d33ac98-b0a7-4eb3-8723-fbf5aee59c30",
                "Professor",
                "Furia",
                "O que importa é a intenção"
            ))

            add(Comment("2dbe9f91-dd70-41f1-9c4d-3bdf90c31577",
                "Professor",
                "INTZ",
                "ainda tentando?"
            ))

            add(Comment("32d690bc-dcac-4fc7-bfd1-83bdc65dffad",
                "Professor",
                "Loud",
                "vocês vão ganhar um belo 10 "
            ))

            add(Comment("559ac723-0dbd-4115-8a3c-3228aaa18bc6",
                "natalia",
                "Kabum",
                "foto legal"
            ))

            add(Comment("559ac723-0dbd-4115-8a3c-3228aaa18bc6",
                "natalia",
                "Kabum",
                "foto legal"
            ))

            add(Comment("9b73b464-93ba-4186-afeb-5b44996b9ffb",
                "natalia",
                "Pain",
                "belo time"
            ))

            add(Comment("af7b29f7-20a0-4ca5-a703-59b7044ff5c2",
                    "natalia",
                    "Flamengo",
                    "esse eu não gosto")
            )

            add(Comment("7027bf35-3712-4619-b603-b9e404d516af",
                "adm mr Hugo",
                "Liberty",
                "liberdade")
            )

            add(Comment("7043909f-2002-4b6b-a215-4ff754135265",
                "Professor",
                "Pain",
                "vocês merecem pelo esforço")
            )

            add(Comment("78b40103-3ede-4e01-b681-67c7cf36e4e0",
                    "hugo",
                    "Loud",
                    "testing"))

            add(Comment("e5ee603d-bccf-4c52-8f3e-649299fd52c1",
                "hugo",
                "Loud",
                "ahsushsu"))

            add(Comment("f3d98fe0-3b03-48e0-936e-ea56fb1e0323",
                "Guilherme",
                "Loud",
                "muito bom de se ver"))
        }

        return list
    }
}
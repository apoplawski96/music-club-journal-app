package com.example.stk47warehousejournalapp.data.model

data class Artist (val id : String, val avatarResource: Int){
    constructor(id : String, firstName : String, lastName : String, avatarResource : Int) : this(id, avatarResource)
}
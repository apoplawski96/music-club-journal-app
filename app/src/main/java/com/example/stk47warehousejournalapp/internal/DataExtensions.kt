package com.example.stk47warehousejournalapp.internal

import com.google.android.gms.tasks.Task
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class DataExtensions {
    internal suspend fun <T> awaitTaskResult(task: Task<T>): T = suspendCoroutine { continuation ->
        task.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(task.result!!)
            } else {
                continuation.resumeWithException(task.exception!!)
            }
        }
    }

    //Wraps Firebase/GMS calls
    internal suspend fun <T> awaitTaskCompletable(task: Task<T>): Unit = suspendCoroutine { continuation ->
        task.addOnCompleteListener { task ->
            if (task.isSuccessful) {
                continuation.resume(Unit)
            } else {
                continuation.resumeWithException(task.exception!!)
            }
        }
    }
}
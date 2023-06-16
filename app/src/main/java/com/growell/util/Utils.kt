package com.growell.util

import java.io.File

object DeleteFileOnExit {
    private var files: LinkedHashSet<String?>? = LinkedHashSet()
    @Synchronized
    fun add(file: File) {
        add(file.path)
    }

    @Synchronized
    fun add(file: String?) {
        if (files == null) return
        files!!.add(file)
    }

    @JvmStatic
    fun runHooks() {
        if (files == null) return
        var theFiles: LinkedHashSet<String?>?
        synchronized(DeleteFileOnExit::class.java) {
            theFiles = files
            files = null
        }
        val toBeDeleted = ArrayList(theFiles)

        // reverse the list to maintain previous jdk deletion order.
        // Last in first deleted.
        toBeDeleted.reverse()
        for (filename in toBeDeleted) {
            try {
                File(filename).delete()
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
}

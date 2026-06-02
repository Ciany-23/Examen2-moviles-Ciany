package com.example.manejobugs

import com.example.manejobugs.data.repository.BugRepository
import com.example.manejobugs.data.repository.MockBugRepository

object AppGraph {
    val bugRepository: BugRepository by lazy { MockBugRepository() }
}

package com.fish.keXieOpenJudge.entity.vo

import com.mybatisflex.core.paginate.Page
import java.io.Serializable

class TopicSolutionVO: Serializable {
    var solutionId: Long? = null
    var title: String? = null
    var solutionContent: String? = null
    var comments: Page<FirstCommentVO>? = null
}
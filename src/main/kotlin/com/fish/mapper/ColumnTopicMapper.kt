package com.fish.mapper

import com.fish.entity.pojo.ColumnTopic
import com.mybatisflex.core.BaseMapper

/**
 * 映射层。
 *
 * @author fish
 * @since 2023-11-14
 */
interface ColumnTopicMapper : BaseMapper<ColumnTopic> {
    fun addColumnTopicBatch(columnTopicList: ArrayList<ColumnTopic>): Int
}

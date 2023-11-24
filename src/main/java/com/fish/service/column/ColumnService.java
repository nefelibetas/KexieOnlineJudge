package com.fish.service.column;

import com.fish.common.Result;
import com.fish.entity.pojo.Column;
import com.fish.entity.vo.ColumnVO;
import com.mybatisflex.core.service.IService;

import java.util.ArrayList;

public interface ColumnService extends IService<Column> {
    Result<?> addColumn(Column column);
    Result<?> addColumnBatch(ArrayList<Column> columns);
    Result<ArrayList<ColumnVO>> getColumns();
    Result<ColumnVO> getColumn(Long columnId);
    Result<?> updateColumn(Column column);
    Result<?> deleteColumn(Long columnId);
    /**
     * 删除特定标签，删除前需要root权限。<br/>
     * 还需要在关联表的记录
     * @param columnId 栏目id
     * @return 封装好的响应信息
     */
    Result<?> deleteColumnReality(Long columnId);
}

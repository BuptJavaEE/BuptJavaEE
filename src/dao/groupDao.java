package dao;

import java.util.List;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-19
 */
public interface groupDao {
    /**
     * 根据小组号查找小组
     * @param groupid
     * @return
     */
    public String queryGroupByGroupId(String groupid);

    /**
     * 根据小组号查询相关人员
     * @param groupid
     * @return
     */
    public List<Integer> queryAuthorsByGroupId(String groupid);
}

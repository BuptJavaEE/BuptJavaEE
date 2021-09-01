package dao;

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
    public String queryGroupByGroupId(int groupid);

    /**
     * 根据组长号查询小组
     * @param groupleader
     * @return
     */
    public String queryGroupByGroupleader(int groupleader);
}

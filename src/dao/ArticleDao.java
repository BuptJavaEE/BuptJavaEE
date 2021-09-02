package dao;

import pojo.Article;

import java.util.List;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-18
 */
public interface ArticleDao {
    /**
     * 根据用户id获取全部文章
     * @param id
     * @return
     */
    public String getArticles(int id);

    /**
     * 自增对应textno的浏览次数
     * @param textno
     */
    public void updateBrowsetimes(int textno);

    /**
     * 根据文章号查询对应的写作小组号
     * @param textno
     * @return
     */
    public int queryGroupidByTextno(int textno);
}

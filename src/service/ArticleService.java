package service;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-19
 */
public interface ArticleService {
    /**
     * 根据用户id加载文章
     * @param id
     * @return
     */
    public String loadArticles(int id);
}

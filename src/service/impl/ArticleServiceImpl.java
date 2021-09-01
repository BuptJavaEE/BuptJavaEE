package service.impl;

import dao.ArticleDao;
import dao.impl.ArticleDaoImpl;
import service.ArticleService;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-08-31-19
 */
public class ArticleServiceImpl implements ArticleService {
    @Override
    public String loadArticles(int id) {
        ArticleDao articleDao = new ArticleDaoImpl();
        return articleDao.getArticles(id);
    }
}

package service.impl;

import dao.CommentDao;
import dao.impl.CommentDaoImpl;
import pojo.Comment;
import service.CommentService;

import java.util.ArrayList;
import java.util.List;

/**
 * 类<code>Doc</code>用于：TODO
 *
 * @author LuoSue
 * @version 1.0
 * @date 2021-09-02-12
 */
public class CommentServiceImpl implements CommentService {
    @Override
    public List<Comment> loadComments(String username) {
        CommentDao commentDao = new CommentDaoImpl();
        return commentDao.queryCommentsByUsername(username);
    }
}

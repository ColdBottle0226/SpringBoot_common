package com.project.single.common.interceptor;

import com.project.single.common.model.CmmnPagingModel;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement mappedStatement = (MappedStatement) args[0];
        BoundSql boundSql = mappedStatement.getBoundSql(args[1]);
        String sql = boundSql.getSql().trim();

        if (boundSql.getParameterObject() instanceof CmmnPagingModel) { // 파라미터가 페이징 타입인 경우만
            CmmnPagingModel parameterObject = (CmmnPagingModel) boundSql.getParameterObject();

            if (parameterObject.getCurrentPage() > 0) { // 전체 개수가 정해진 쿼리만 페이징 조건 추가
                BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), getPagingSQL(sql, (CmmnPagingModel) boundSql.getParameterObject()),
                        boundSql.getParameterMappings(), boundSql.getParameterObject());
                MappedStatement newMappedStatement = new MappedStatement.Builder(mappedStatement.getConfiguration(), mappedStatement.getId(),
                        param -> newBoundSql, mappedStatement.getSqlCommandType()).resource(mappedStatement.getResource())
                        .fetchSize(mappedStatement.getFetchSize()).statementType(mappedStatement.getStatementType())
                        .keyGenerator(mappedStatement.getKeyGenerator()).timeout(mappedStatement.getTimeout())
                        .parameterMap(mappedStatement.getParameterMap()).resultMaps(mappedStatement.getResultMaps())
                        .resultSetType(mappedStatement.getResultSetType()).cache(mappedStatement.getCache())
                        .flushCacheRequired(mappedStatement.isFlushCacheRequired()).useCache(mappedStatement.isUseCache()).build();
                args[0] = newMappedStatement;
            }
        }
        return invocation.proceed();
    }

    // 페이징 조건 추가
    public String getPagingSQL(String sql, CmmnPagingModel so) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(1) over() as current_page_cnt, pagi.*\n");
        sb.append("  from (select count(1) over() as total_cnt, row_number() over() as rnum, tmp.*\n");
        sb.append("          from (");
        sb.append(sql);
        sb.append("\n              ) tmp\n");
        sb.append("        ) pagi\n");
        sb.append(" where pagi.rnum between " + so.getFirstIndex() + " and " + so.getLastIndex());
        return sb.toString();
    }

   }


package smart.poc.intercepter;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.mapping.MappedStatement;
import java.sql.Connection;
import java.util.*;

@Intercepts({
		@Signature(type = StatementHandler.class, method = "prepare", args = { Connection.class, Integer.class }) })
public class PageInterceptor implements Interceptor {

	private int pageSize;
	private int currPage;
	private int limit;

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject MetaObjectHandler = SystemMetaObject.forObject(statementHandler);
		// 获取statementHandler包装类
		while (MetaObjectHandler.hasGetter("")) {
			Object obj = MetaObjectHandler.getValue("h");
			MetaObjectHandler = SystemMetaObject.forObject(obj);
		}
		while (MetaObjectHandler.hasGetter("target")) {
			Object obj = MetaObjectHandler.getValue("target");
			MetaObjectHandler = SystemMetaObject.forObject(obj);
		}

		MappedStatement mappedStatement = (MappedStatement) MetaObjectHandler.getValue("delegate.mappedStatement");
		String mapId = mappedStatement.getId();

		if (mapId.matches(".+ByPage$")) {
			// 获取进行数据库操作时管理参数的handler
			ParameterHandler parameterHandler = (ParameterHandler) MetaObjectHandler
					.getValue("delegate.parameterHandler");
			// 获取请求时的参数
			@SuppressWarnings("unchecked")
			Map<String, Object> paraObject = (Map<String, Object>) parameterHandler.getParameterObject();
			// 1也可以这样获取
			// paraObject = (Map<String, Object>) statementHandler.getBoundSql() .get
			// 参数名称和在service中设置到map中的名称一致
			currPage = (int) paraObject.get("currPage");
			pageSize = (int) paraObject.get("pageSize");
			String sql = (String) MetaObjectHandler.getValue("delegate.boundSql.sql");
			// 1也可以通过statementHandler直接获取
			// sql = statementHandler.getBoundSql().getSql();
			// 1/构建分页功能的sqL语句

			sql = sql.trim();
			String limitSql = sql + " limit " + (currPage - 1) * pageSize + "," + pageSize;
			// 1/将构建完成的分页sq1 语句赋值个体"delegate.boundsql.sql'，偷天换日
			MetaObjectHandler.setValue("delegate.boundSql.sql", limitSql);
			// 1/ 调用原对象的方法，进入责任链的下一级
		}
		return invocation.proceed();
	}

	@Override
	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}
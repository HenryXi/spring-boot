# Why batchUpdate return int[][] instead of int[]
There are multiple ``batchUpdate`` methods in JdbcTemplate. I found most of them return ``int[]``. Only
one method with ``ParameterizedPreparedStatementSetter`` parameter return ``int[][]`` instead of ``int[]``.
It makes me wonder. ``int[]`` means the number of rows affected by each statement. ``int[]`` is enough
why use ``int[][]``.

The source code is here
```
if (logger.isDebugEnabled()) {
        logger.debug("Executing SQL batch update [" + sql + "] with a batch size of " + batchSize);
    }
    return execute(sql, new PreparedStatementCallback<int[][]>() {
        @Override
        public int[][] doInPreparedStatement(PreparedStatement ps) throws SQLException {
            List<int[]> rowsAffected = new ArrayList<int[]>();
            try {
                boolean batchSupported = true;
                if (!JdbcUtils.supportsBatchUpdates(ps.getConnection())) {
                    batchSupported = false;
                    logger.warn("JDBC Driver does not support Batch updates; resorting to single statement execution");
                }
                int n = 0;
                for (T obj : batchArgs) {
                    pss.setValues(ps, obj);
                    n++;
                    if (batchSupported) {
                        ps.addBatch();
                        if (n % batchSize == 0 || n == batchArgs.size()) {
                            if (logger.isDebugEnabled()) {
                                int batchIdx = (n % batchSize == 0) ? n / batchSize : (n / batchSize) + 1;
                                int items = n - ((n % batchSize == 0) ? n / batchSize - 1 : (n / batchSize)) * batchSize;
                                logger.debug("Sending SQL batch update #" + batchIdx + " with " + items + " items");
                            }
                            rowsAffected.add(ps.executeBatch());
                        }
                    }
                    else {
                        int i = ps.executeUpdate();
                        rowsAffected.add(new int[] {i});
                    }
                }
                int[][] result = new int[rowsAffected.size()][];
                for (int i = 0; i < result.length; i++) {
                    result[i] = rowsAffected.get(i);
                }
                return result;
            } finally {
                if (pss instanceof ParameterDisposer) {
                    ((ParameterDisposer) pss).cleanupParameters();
                }
            }
        }
    });
```

We can see the most important code is ``rowsAffected.add(ps.executeBatch());``. If JDBC Driver support
``batchUpdate`` JdbcTemplate will invoke ``executeBatch()`` method or JdbcTemplate will execute statement
**one by one**(I call it fake ``batchUpdate``). ``executeBatch()`` will return ``int[]`` but ``executeUpdate()``
will return ``int``. So this batchUpdate method return ``int[][]`` to apply to both cases. In other words different result will 
prove the JDBC Driver support batch update or not.

**Summary**

If the JDBC Driver support "batch update" the result will be like following
```
[[1, 1, 1]]
```
if the JDBC Driver not support "batch update" the result will be like following
```
[[1], [1], [1]]
```
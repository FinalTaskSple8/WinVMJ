echo SELECT 'CREATE DATABASE siph_product_variasi1' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'siph_product_variasi1') \gexec | psql "postgresql://postgres:yudisabri123@localhost"
for %%G in (sql/*.sql) do psql -a -f sql/%%G "postgresql://postgres:yudisabri123@localhost/siph_product_variasi1"

java -cp siph.product.variasi1 --module-path siph.product.variasi1 -m siph.product.variasi1
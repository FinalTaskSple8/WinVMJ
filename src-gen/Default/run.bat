echo SELECT 'CREATE DATABASE siph_product_default' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'siph_product_default') \gexec | psql "postgresql://postgres:yudisabri123@localhost"
for %%G in (sql/*.sql) do psql -a -f sql/%%G "postgresql://postgres:yudisabri123@localhost/siph_product_default"

java -cp siph.product.default --module-path siph.product.default -m siph.product.default
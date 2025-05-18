echo SELECT 'CREATE DATABASE siph_product_variasisatu' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'siph_product_variasisatu') \gexec | psql "postgresql://postgres:claraadpro123@localhost"
for %%G in (sql/*.sql) do psql -a -f sql/%%G "postgresql://postgres:claraadpro123@localhost/siph_product_variasisatu"

java -cp siph.product.variasisatu --module-path siph.product.variasisatu -m siph.product.variasisatu
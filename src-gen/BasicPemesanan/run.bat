echo SELECT 'CREATE DATABASE siph_product_basicpemesanan' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'siph_product_basicpemesanan') \gexec | psql "postgresql://postgres:123456@localhost"
for %%G in (sql/*.sql) do psql -a -f sql/%%G "postgresql://postgres:123456@localhost/siph_product_basicpemesanan"

java -cp siph.product.basicpemesanan --module-path siph.product.basicpemesanan -m siph.product.basicpemesanan
#!/bin/bash

cleanup() {
    echo "Exiting script..."
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

read -p "Enter the path to the frontend directory: " frontend_dir

echo "SELECT 'CREATE DATABASE siph_product_variasi1' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'siph_product_variasi1') \gexec" | psql "postgresql://postgres:yudisabri123@localhost"
for file in sql/*.sql; do
    psql -a -f "$file" "postgresql://postgres:yudisabri123@localhost/siph_product_variasi1"
done

java -cp siph.product.variasi1 --module-path siph.product.variasi1 -m siph.product.variasi1 &

cd $frontend_dir && {
    npm install && {
        npm run json:server &
        npm run start &
    }
}

wait
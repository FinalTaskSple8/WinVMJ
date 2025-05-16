#!/bin/bash

cleanup() {
    echo "Exiting script..."
    pkill -P $$
    exit 1
}

trap cleanup SIGINT

read -p "Enter the path to the frontend directory: " frontend_dir

echo "SELECT 'CREATE DATABASE siph_product_basicpemesanan' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'siph_product_basicpemesanan') \gexec" | psql "postgresql://postgres:claraadpro123@localhost"
for file in sql/*.sql; do
    psql -a -f "$file" "postgresql://postgres:claraadpro123@localhost/siph_product_basicpemesanan"
done

java -cp siph.product.basicpemesanan --module-path siph.product.basicpemesanan -m siph.product.basicpemesanan &

cd $frontend_dir && {
    npm install && {
        npm run json:server &
        npm run start &
    }
}

wait